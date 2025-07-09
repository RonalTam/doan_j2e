/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.example.doan_web_j2e;

import com.example.doan_web_j2e.data.dao.CategoryDao;
import com.example.doan_web_j2e.data.model.Category;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.example.doan_web_j2e.data.dao.DatabaseDao;
import com.example.doan_web_j2e.data.dao.ProductDao;
import com.example.doan_web_j2e.data.model.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Welcome
 */
public class SearchServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String keyword = request.getParameter("keyword");
        String categoryParam = request.getParameter("categoryId");
        String categoryName = "";

        ProductDao productDao = DatabaseDao.getInstance().getProductDao();
        CategoryDao categoryDao = DatabaseDao.getInstance().getCategoryDao();
        List<Category> categoryList = categoryDao.findAll();
        request.setAttribute("categoryList", categoryList);

        List<Product> productList = new ArrayList<>();

        if (categoryParam != null && !categoryParam.isEmpty()) {
            int categoryId = Integer.parseInt(categoryParam);

            // Lấy category từ DB
            Category category = categoryDao.find(categoryId); // bạn cần có hàm này

            if (category != null) {
                categoryName = category.getName(); // ✅ Lấy tên danh mục từ DB
                request.setAttribute("categoryId", categoryId);
                request.setAttribute("categoryName", categoryName);
            }
        }

        boolean hasKeyword = keyword != null && !keyword.trim().isEmpty();
        boolean hasCategory = categoryParam != null && !categoryParam.isEmpty();

        if (hasKeyword && hasCategory) {
            int categoryId = Integer.parseInt(categoryParam);
            productList = productDao.findByCategoryOfName(categoryId, keyword);
            request.setAttribute("categoryId", categoryId);
        } else if (hasKeyword) {
            productList = productDao.findByName(keyword);
        } else if (hasCategory) {
            int categoryId = Integer.parseInt(categoryParam);
            productList = productDao.findByCategory(categoryId); // ⚠️ bạn cần tạo hàm này nếu chưa có
            request.setAttribute("categoryId", categoryId);
        } else {
            productList = productDao.findAll(); // fallback nếu không nhập gì cả
        }

        request.setAttribute("keyword", keyword);
        request.setAttribute("productList", productList);

        request.getRequestDispatcher("shop.jsp").include(request, response);
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
