/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.example.doan_web_j2e;

import com.example.doan_web_j2e.data.dao.ProductDao;
import com.example.doan_web_j2e.data.model.Product;
import com.example.doan_web_j2e.util.Helper;
import jakarta.mail.MessagingException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.example.doan_web_j2e.data.dao.DatabaseDao;
import com.example.doan_web_j2e.data.dao.OrderDao;
import com.example.doan_web_j2e.data.dao.OrderItemDao;
import com.example.doan_web_j2e.data.model.Order;
import com.example.doan_web_j2e.data.model.OrderItem;
import com.example.doan_web_j2e.data.model.User;
import com.example.doan_web_j2e.util.StringHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Welcome
 */
public class CheckoutServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Set the content type for the response
        response.setContentType("text/html;charset=UTF-8");

        // Lấy session và kiểm tra người dùng
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        List<OrderItem> cart = (List<OrderItem>) session.getAttribute("cart");
        if(cart == null){
            cart = new ArrayList<OrderItem>();
        }
        if (user == null) {
            // Chưa đăng nhập thì chuyển hướng về trang đăng nhập
            response.sendRedirect("LoginServlet");
        } else {
            // Đã đăng nhập thì cho vào trang checkout
            request.getRequestDispatcher("checkout.jsp").forward(request, response);
        }

        request.setAttribute("cart", cart);
        request.setAttribute("total", Helper.total(cart));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Lấy dữ liệu người dùng nhập
        String fullname = request.getParameter("fullname");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String email = request.getParameter("email");


        proccessCheckout(request, user, fullname, phone, email, address);

        session.setAttribute("redirect", "cart.jsp"); // hoặc trang bạn muốn chuyển đến
        response.sendRedirect("success.jsp"); // chuyển đến trang hiển thị thông báo

    }

    private void proccessCheckout(HttpServletRequest request, User user, String fullname, String phone, String email, String address) {
        OrderDao orderDao = DatabaseDao.getInstance().getOrderDao();
        ProductDao productDao = DatabaseDao.getInstance().getProductDao();
        String code = StringHelper.randomString(10);

        Order order = new Order(code, "pending", user.getId(), fullname, phone, email, address);
        orderDao.insert(order);
        Order savedOrder = orderDao.findByCode(order.getCode());
        order = orderDao.findByCode(code);
        HttpSession session = request.getSession();
        List<OrderItem> cart = (List<OrderItem>) session.getAttribute("cart");

        OrderItemDao orderItemDao = DatabaseDao.getInstance().getOrderItemDao();
        if (cart != null) {
            for (OrderItem orderItem : cart) {
                orderItem.setOrderId(order.getId());
                orderItemDao.insert(orderItem);
            }
        }

        String subject = "Xác nhận đơn hàng #" + savedOrder.getCode();
        String content = "<!DOCTYPE html>"
                + "<html>"
                + "<head>"
                + "<meta charset='UTF-8'>"
                + "<style>"
                + "  body { font-family: Arial, sans-serif; line-height: 1.6; }"
                + "  .container { max-width: 600px; margin: auto; padding: 20px; border: 1px solid #ddd; border-radius: 10px; }"
                + "  h2 { color: #2c3e50; }"
                + "  p { margin: 8px 0; }"
                + "  .footer { margin-top: 20px; font-size: 12px; color: #888; }"
                + "</style>"
                + "</head>"
                + "<body>"
                + "<div class='container'>"
                + "<h2>Xác nhận đơn hàng #" + savedOrder.getCode() + "</h2>"
                + "<p>Xin chào <strong>" + savedOrder.getFullname() + "</strong>,</p>"
                + "<p>Cảm ơn bạn đã đặt hàng tại cửa hàng của chúng tôi!</p>"
                + "<p><strong>Thông tin đơn hàng:</strong></p>"
                + "<ul>"
                + "<li><strong>Mã đơn hàng:</strong> " + savedOrder.getCode() + "</li>"
                + "<li><strong>Họ tên:</strong> " + savedOrder.getFullname() + "</li>"
                + "<li><strong>Số điện thoại:</strong> " + savedOrder.getPhone() + "</li>"
                + "<li><strong>Email:</strong> " + savedOrder.getEmail() + "</li>"
                + "<li><strong>Địa chỉ:</strong> " + savedOrder.getAddress() + "</li>"
                + "<li><strong>Ngày đặt:</strong> " + savedOrder.getCreatedAt() + "</li>"
                + "</ul>"
                + "<p>Chúng tôi sẽ xử lý đơn hàng của bạn trong thời gian sớm nhất.</p>"
                + "<p>Trân trọng,<br>Đội ngũ hỗ trợ khách hàng</p>"
                + "<div class='footer'>Vui lòng không trả lời email này.</div>"
                + "</div>"
                + "</body>"
                + "</html>";

        try {
            EmailUtil.sendEmail(savedOrder.getEmail(), subject, content);
        } catch (MessagingException e) {
            e.printStackTrace(); // Hoặc log lỗi gửi mail
        }

        session.setAttribute("message", "Checkout thành công, đã gửi xác nhận về email!");
        session.removeAttribute("cart");
    }
}