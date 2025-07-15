/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.example.doan_web_j2e.admin.order;

import com.example.doan_web_j2e.admin.BaseAdminServlet;
import com.example.doan_web_j2e.data.dao.*;
import com.example.doan_web_j2e.data.model.Category;
import com.example.doan_web_j2e.data.model.Order;
import com.example.doan_web_j2e.data.model.OrderItem;
import com.example.doan_web_j2e.data.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Welcome
 */
public class EditOrderServlet extends BaseAdminServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        String idStr = request.getParameter("orderId");
        if (idStr == null || idStr.isBlank()) {
            response.sendRedirect("IndexOrderServlet");
            return;
        }

        int orderId = Integer.parseInt(idStr);
        Order order = DatabaseDao.getInstance().getOrderDao().find(orderId);

        if (order == null) {                           // không tìm thấy
            response.sendRedirect("IndexOrderServlet");
            return;
        }

        request.setAttribute("order", order);
        request.getRequestDispatcher("/admin/order/edit.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int orderId = Integer.parseInt(request.getParameter("orderId"));
        String newStatus = request.getParameter("status");

        OrderDao orderDao = DatabaseDao.getInstance().getOrderDao();
        OrderItemDao orderItemDao = DatabaseDao.getInstance().getOrderItemDao();
        ProductDao productDao = DatabaseDao.getInstance().getProductDao();

        Order order = orderDao.find(orderId);

        // Chỉ trừ số lượng khi đơn chuyển từ trạng thái khác sang "finished"
        if (!"finished".equals(order.getStatus()) && "finished".equals(newStatus)) {
            List<OrderItem> items = orderItemDao.findByOrderId(orderId);

            for (OrderItem item : items) {
                Product product = productDao.find(item.getProductId());

                int currentQty = product.getQuantity();
                int soldQty = item.getQuantity();

                int newQty = currentQty - soldQty;
                if (newQty < 0) {
                    newQty = 0; // tránh âm nếu thiếu hàng
                }

                product.setQuantity(newQty);
                productDao.update(product);
            }
        }

        // Cập nhật trạng thái đơn hàng
        order.setStatus(newStatus);
        orderDao.update(order);

        response.sendRedirect("IndexOrderServlet");
    }

}
