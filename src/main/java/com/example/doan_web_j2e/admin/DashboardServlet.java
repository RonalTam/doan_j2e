/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.example.doan_web_j2e.admin;

import com.example.doan_web_j2e.data.dao.DatabaseDao;
import com.example.doan_web_j2e.data.dao.OrderDao;
import com.example.doan_web_j2e.data.model.Order;
import com.example.doan_web_j2e.data.model.User;
import com.example.doan_web_j2e.util.GetDateTime;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Welcome
 */
public class DashboardServlet extends BaseAdminServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false); // false: không tạo mới session nếu chưa có

        // Kiểm tra xem người dùng đã đăng nhập chưa
        if (session == null || session.getAttribute("user") == null) {
            // Chưa đăng nhập → chuyển hướng về Login
            response.sendRedirect("LoginServlet");
            return;
        }
        User user = (User) session.getAttribute("user");

        // (Tùy chọn) kiểm tra vai trò nếu muốn chỉ admin vào
        if (!user.getRole().equals("admin")) {
            response.sendRedirect("index.jsp"); // không phải admin thì về trang chính
            return;
        }

        OrderDao orderDao = DatabaseDao.getInstance().getOrderDao();
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        int numberOrderToday = orderDao.countOrderByDay(dtf.format(now));
        
        List<Order> orderList = orderDao.findAll();
        int numberOrderAll = orderList.size();
        List<Order> orderPendingList = orderDao.findByStatus("pending");
        
        int numberOrderPending = orderPendingList.size();
        int numberOrderFinished = numberOrderAll - numberOrderPending;        
        
        
        request.setAttribute("numberOrderToday", numberOrderToday);
        request.setAttribute("numberOrderAll", numberOrderAll);
        request.setAttribute("numberOrderPending", numberOrderPending);
        request.setAttribute("numberOrderFinished", numberOrderFinished);
        
//        Chart
        List<String> dateList = GetDateTime.get7Date();
        List<Integer> orderByDateList = new ArrayList<>();
        for (String d : dateList) {
            int n = orderDao.countOrderByDay(d);
            orderByDateList.add(n);
        }
        
        request.setAttribute("dateList", dateList);
        request.setAttribute("orderByDateList", orderByDateList);
        request.setAttribute("orderPendingList", orderPendingList);
        request.getRequestDispatcher("admin/dashboard.jsp").include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
