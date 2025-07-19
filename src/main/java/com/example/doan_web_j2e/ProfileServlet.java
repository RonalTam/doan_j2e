package com.example.doan_web_j2e;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            // Nếu chưa đăng nhập thì chuyển hướng đến login
            response.sendRedirect("LoginServlet");
            return;
        }

        // Nếu đã đăng nhập thì chuyển đến trang profile
        request.getRequestDispatcher("profile.jsp").forward(request, response);
    }
}
