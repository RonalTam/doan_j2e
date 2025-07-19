package com.example.doan_web_j2e;
import com.example.doan_web_j2e.data.dao.DatabaseDao;
import com.example.doan_web_j2e.data.dao.UserDAO;
import com.example.doan_web_j2e.data.model.User;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@WebServlet("/LoginGoogleHandler")
public class LoginGoogleHandler extends HttpServlet {

    private static final String CLIENT_ID = "402368277332-1hbu7sh1jodol11pili71ec7psf6qh1j.apps.googleusercontent.com";
    private static final String CLIENT_SECRET = "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";
    private static final String REDIRECT_URI = "http://localhost:8080/doan_web_j2e_war_exploded/LoginGoogleHandler";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String code = request.getParameter("code");

        if (code == null || code.isEmpty()) {
            // Bước 1: Chuyển hướng đến Google
            String authURL = new GoogleAuthorizationCodeRequestUrl(
                    CLIENT_ID,
                    REDIRECT_URI,
                    Collections.singleton("openid email profile")
            ).build();

            response.sendRedirect(authURL);
        } else {
            // Bước 2: Nhận token từ Google
            GoogleTokenResponse tokenResponse = new GoogleAuthorizationCodeTokenRequest(
                    new NetHttpTransport(),
                    GsonFactory.getDefaultInstance(),
                    "https://oauth2.googleapis.com/token",
                    CLIENT_ID,
                    CLIENT_SECRET,
                    code,
                    REDIRECT_URI
            ).execute();

            String idTokenString = tokenResponse.getIdToken();

            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
                    new NetHttpTransport(), GsonFactory.getDefaultInstance())
                    .setAudience(Collections.singletonList(CLIENT_ID))
                    .build();

            GoogleIdToken idToken = null;
            try {
                idToken = verifier.verify(idTokenString);
            } catch (GeneralSecurityException e) {
                throw new RuntimeException(e);
            }

            if (idToken != null) {
                GoogleIdToken.Payload payload = idToken.getPayload();

                String email = payload.getEmail();
                String name = (String) payload.get("name"); // nếu cần
                String pictureUrl = (String) payload.get("picture");

                UserDAO userDao = DatabaseDao.getInstance().getUserDao();
                User user = userDao.find(email);

                if (user == null) {
                    // Nếu email chưa có, tạo mới tài khoản
                    user = new User(email, "", "user"); // Mật khẩu có thể để trống hoặc mã hoá một chuỗi ngẫu nhiên
                    userDao.insert(user);
                }

                HttpSession session = request.getSession();
                session.setAttribute("user", user);

                request.setAttribute("message", "Login successful! Redirecting to main page...");
                request.setAttribute("redirect", "index.jsp");
                request.getRequestDispatcher("success.jsp").forward(request, response);
            } else {
                response.getWriter().println("❌ Token không hợp lệ");
            }
        }
    }
}

