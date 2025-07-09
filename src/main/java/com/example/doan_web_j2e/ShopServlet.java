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
import com.example.doan_web_j2e.util.Constants;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author Welcome
 */
public class ShopServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ProductDao productDao = DatabaseDao.getInstance().getProductDao();
        CategoryDao categoryDao = DatabaseDao.getInstance().getCategoryDao();

        // Lấy danh sách danh mục để hiển thị dropdown
        List<Category> categoryList = categoryDao.findAll();
        request.setAttribute("categoryList", categoryList);

        // Lọc theo category nếu có
        String categoryIdRaw = request.getParameter("categoryId");
        Integer categoryId = null;
        if (categoryIdRaw != null && !categoryIdRaw.isEmpty()) {
            categoryId = Integer.parseInt(categoryIdRaw);
            request.setAttribute("selectedCategoryId", categoryId); // để chọn lại trong dropdown
        }

        // Xử lý phân trang
        int page = 1;
        int perPage = Constants.PER_PAGE;

        if (request.getParameter("page") != null) {
            try {
                page = Integer.parseInt(request.getParameter("page"));
            } catch (NumberFormatException e) {
                page = 1;
            }
        }

        int totalProducts = (categoryId == null)
                ? productDao.countAll()
                : productDao.countByCategory(categoryId); // bạn cần có hàm này

        int numberPage = (int) Math.ceil((double) totalProducts / perPage);
        int offset = (page - 1) * perPage;

        List<Product> productList = (categoryId == null)
                ? productDao.getProducts(offset, perPage)
                : productDao.getProductsByCategory(categoryId, offset, perPage); // bạn cần có hàm này

        // Gửi dữ liệu sang JSP
        request.setAttribute("productList", productList);
        request.setAttribute("page", page);
        request.setAttribute("numberPage", numberPage);

        request.getRequestDispatcher("shop.jsp").include(request, response);
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
