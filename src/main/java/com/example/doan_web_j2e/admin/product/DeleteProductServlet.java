/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.example.doan_web_j2e.admin.product;

import com.example.doan_web_j2e.admin.BaseAdminServlet;
import com.example.doan_web_j2e.data.dao.DatabaseDao;
import com.example.doan_web_j2e.data.dao.ProductDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 *
 * @author Welcome
 */
public class DeleteProductServlet extends BaseAdminServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));
        ProductDao productDao = DatabaseDao.getInstance().getProductDao();
        productDao.delete(productId);
        
        response.sendRedirect("IndexProductServlet");
    }

}
