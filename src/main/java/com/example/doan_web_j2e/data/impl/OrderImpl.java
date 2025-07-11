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
        String sql = "INSERT INTO ORDERS(ID, CODE, STATUS, USERID) VALUES(NULL, ?, ?, ?)";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, order.getCode());
            stmt.setString(2, order.getStatus());
            stmt.setInt(3, order.getUserId());

            stmt.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Order order) {
        String sql = "UPDATE ORDERS SET code = ?, status = ?, userid = ?, createdat = ? WHERE id = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, order.getCode());
            stmt.setString(2, order.getStatus());
            stmt.setInt(3, order.getUserId());
            stmt.setTimestamp(4, order.getCreatedAt());
            stmt.setInt(5, order.getId());
            return stmt.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        // TODO Auto-generated method stub
        String sql = "DELETE FROM ORDERS WHERE ID = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);

            return stmt.execute();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
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
                int userId = rs.getInt("user_id");
                Timestamp created_at = rs.getTimestamp("created_at");

                return new Order(id, code, status, userId, created_at);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Order> findAll() {
        // TODO Auto-generated method stub
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

                orders.add(new Order(id, code, status, userId, createdAt));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
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

                orderList.add(new Order(id, code, status, userId, createdAt));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return orderList;
    }

    @Override
    public List<Order> findByStatus(String status) {
        List<Order> orderList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM ORDERS WHERE STATUS = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, status);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String code = rs.getString("code");
                int userId = rs.getInt("userid");
                Timestamp createdAt = rs.getTimestamp("createdat");
                orderList.add(new Order(id, code, status, userId, createdAt));
            }
        } catch (SQLException ex) {
        }

        return orderList;
    }

    @Override
    public Order findByCode(String code) {
        String sql = "SELECT * FROM ORDERS WHERE CODE= ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, code);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String status = rs.getString("status");
                int userId = rs.getInt("userid");
                Timestamp createdAt = rs.getTimestamp("createdat");

                return new Order(id, code, status, userId, createdAt);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int countOrderByDay(String date) {
        int count = 0;
        String sql = "SELECT COUNT(*) AS count FROM orders where date(createdat)=?";
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
        String sql = "SELECT * FROM orders where date(createdat)=?";
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

                Order order = new Order(id, code, status, userId, createdAt);
//				total += order.getTotal();
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }

}
