/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.example.doan_web_j2e.admin.user;

import com.example.doan_web_j2e.admin.BaseAdminServlet;
import com.example.doan_web_j2e.data.dao.DatabaseDao;
import com.example.doan_web_j2e.data.dao.UserDAO;
import com.example.doan_web_j2e.data.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
/**
 *
 * @author Welcome
 */
public class IndexUserServlet extends BaseAdminServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDAO userDAO = DatabaseDao.getInstance().getUserDao();
        List<User> userList = userDAO.findAll();
        
        request.setAttribute("userList", userList);        
        request.getRequestDispatcher("admin/user/index.jsp").include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
