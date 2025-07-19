package com.example.doan_web_j2e.data.impl;

import com.example.doan_web_j2e.data.dao.OrderDao;
import com.example.doan_web_j2e.data.driver.MySQLDriver;
import com.example.doan_web_j2e.data.model.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrderImpl implements OrderDao {

    Connection con = MySQLDriver.getInstance().getConnection();

    @Override
    public boolean insert(Order order) {
        String sql = "INSERT INTO orders (code, status, userid, fullname, phone, email, address, createdat) VALUES (?, ?, ?, ?, ?, ?, ?, NOW())";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, order.getCode());
            stmt.setString(2, order.getStatus());
            stmt.setInt(3, order.getUserId());
            stmt.setString(4, order.getFullname());
            stmt.setString(5, order.getPhone());
            stmt.setString(6, order.getEmail());
            stmt.setString(7, order.getAddress());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Order order) {
        String sql = "UPDATE ORDERS SET code = ?, status = ?, userid = ?, createdat = ?, fullname = ?, phone = ?, address = ? WHERE id = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, order.getCode());
            stmt.setString(2, order.getStatus());
            stmt.setInt(3, order.getUserId());
            stmt.setTimestamp(4, order.getCreatedAt());
            stmt.setString(5, order.getFullname());
            stmt.setString(6, order.getPhone());
            stmt.setString(7, order.getAddress());
            stmt.setInt(8, order.getId());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM ORDERS WHERE ID = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Order find(int id) {
        String sql = "SELECT * FROM ORDERS WHERE ID = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String code = rs.getString("code");
                String status = rs.getString("status");
                int userId = rs.getInt("userId");
                Timestamp createdAt = rs.getTimestamp("createdAt");
                String fullname = rs.getString("fullname");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String address = rs.getString("address");

                return new Order(id, code, status, userId, createdAt, fullname, phone, email, address);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Order> findAll() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM ORDERS";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String code = rs.getString("code");
                String status = rs.getString("status");
                int userId = rs.getInt("userid");
                Timestamp createdAt = rs.getTimestamp("createdat");
                String fullname = rs.getString("fullname");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String address = rs.getString("address");

                orders.add(new Order(id, code, status, userId, createdAt, fullname, phone, email, address));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    @Override
    public List<Order> findByUser(int userId) {
        List<Order> orderList = new ArrayList<>();
        String sql = "SELECT * FROM ORDERS WHERE userid = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String code = rs.getString("code");
                String status = rs.getString("status");
                Timestamp createdAt = rs.getTimestamp("createdat");
                String fullname = rs.getString("fullname");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String address = rs.getString("address");

                orderList.add(new Order(id, code, status, userId, createdAt, fullname, phone, email, address));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }

    @Override
    public List<Order> findByStatus(String status) {
        List<Order> orderList = new ArrayList<>();
        String sql = "SELECT * FROM ORDERS WHERE STATUS = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, status);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String code = rs.getString("code");
                int userId = rs.getInt("userid");
                Timestamp createdAt = rs.getTimestamp("createdat");
                String fullname = rs.getString("fullname");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String address = rs.getString("address");

                orderList.add(new Order(id, code, status, userId, createdAt, fullname, phone, email, address));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }

    @Override
    public Order findByCode(String code) {
        String sql = "SELECT * FROM orders WHERE code = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, code);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Order(
                        rs.getInt("id"),
                        rs.getString("code"),
                        rs.getString("status"),
                        rs.getInt("userid"),
                        rs.getTimestamp("createdat"),
                        rs.getString("fullname"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("address")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int countOrderByDay(String date) {
        int count = 0;
        String sql = "SELECT COUNT(*) AS count FROM orders WHERE DATE(createdat) = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, date);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    @Override
    public double earningOrderByDay(String date) {
        double total = 0;
        String sql = "SELECT * FROM orders WHERE DATE(createdat) = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, date);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String code = rs.getString("code");
                String status = rs.getString("status");
                int userId = rs.getInt("userid");
                Timestamp createdAt = rs.getTimestamp("createdat");
                String fullname = rs.getString("fullname");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String address = rs.getString("address");

                Order order = new Order(id, code, status, userId, createdAt, fullname, phone, email, address);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }
}
