/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.example.doan_web_j2e;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.example.doan_web_j2e.data.dao.DatabaseDao;
import com.example.doan_web_j2e.data.dao.UserDAO;
import com.example.doan_web_j2e.data.model.User;

import java.io.IOException;

/**
 *
 * @author Welcome
 */
public class RegisterServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("register.jsp").include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

// Kiểm tra độ dài mật khẩu
        if (password == null || password.length() <= 7) {
            session.setAttribute("error", "Password must be more than 7 characters");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

// Kiểm tra xác nhận mật khẩu
        if (!password.equals(confirmPassword)) {
            session.setAttribute("error", "Passwords do not match");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        UserDAO userDao = DatabaseDao.getInstance().getUserDao();
        User user = userDao.find(email);

        if (user != null) {
            session.setAttribute("error", "Email already exists");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } else {
            user = new User(email, password, "user");
            userDao.insert(user);
            request.setAttribute("message", "Registration successful! Redirecting to login page...");
            request.setAttribute("redirect", "LoginServlet"); // hoặc "index.jsp"
            request.getRequestDispatcher("success.jsp").forward(request, response);

        }
    }


}
