///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
// */
//package com.example.doan_web_j2e;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
//import com.example.doan_web_j2e.data.dao.DatabaseDao;
//import com.example.doan_web_j2e.data.dao.OrderDao;
//import com.example.doan_web_j2e.data.dao.OrderItemDao;
//import com.example.doan_web_j2e.data.model.Order;
//import com.example.doan_web_j2e.data.model.OrderItem;
//import com.example.doan_web_j2e.data.model.User;
//import com.example.doan_web_j2e.util.StringHelper;
//
//import java.io.IOException;
//import java.util.List;
//
///**
// *
// * @author Welcome
// */
//public class FinishCheckoutServlet extends BaseServlet {
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        User user = (User) session.getAttribute("user");
//
//        if(user == null){
//            response.sendRedirect("LoginServlet");
//        }else{
//            proccessCheckout(request, user);
//            response.sendRedirect("CartServlet");
//        }
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//    }
//
//    private void proccessCheckout(HttpServletRequest request, User user) {
//        OrderDao orderDao = DatabaseDao.getInstance().getOrderDao();
//        String code = StringHelper.randomString(10);
//        Order order = new Order(code, "pending", user.getId());
//        orderDao.insert(order);
//
//        order = orderDao.findByCode(code);
//        HttpSession session = request.getSession();
//        List<OrderItem> cart = (List<OrderItem>) session.getAttribute("cart");
//
//        OrderItemDao orderItemDao = DatabaseDao.getInstance().getOrderItemDao();
//
//        if(cart != null){
//            for(OrderItem orderItem : cart){
//                orderItem.setOrderId(order.getId());
//                orderItemDao.insert(orderItem);
//            }
//        }
//
//        session.setAttribute("message", "Checkout Success");
//        session.removeAttribute("cart");
//    }
//
//}
