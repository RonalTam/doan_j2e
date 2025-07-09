package com.example.doan_web_j2e.data.impl;

import com.example.doan_web_j2e.data.dao.OrderItemDao;
import com.example.doan_web_j2e.data.driver.MySQLDriver;
import com.example.doan_web_j2e.data.model.OrderItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderItemImpl implements OrderItemDao {
	Connection con = MySQLDriver.getInstance().getConnection();

	@Override
	public boolean insert(OrderItem orderItem) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO ORDER_ITEMS(ID, QUANTITY, PRICE, ORDERID, PRODUCTID) VALUES(NULL, ?, ?, ?, ?)";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, orderItem.getQuantity());
			stmt.setDouble(2, orderItem.getPrice());
			stmt.setInt(3, orderItem.getOrderId());
			stmt.setInt(4, orderItem.getProductId());
			
			stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(OrderItem orderItem) {
		// TODO Auto-generated method stub
		String sql = "UPDATE ORDER_ITEMS SET quantity = ?, price = ?, orderid = ?, productid = ? WHERE id = ?";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1,orderItem.getQuantity());
			stmt.setDouble(2, orderItem.getPrice());
			stmt.setInt(3, orderItem.getOrderId());
			stmt.setInt(4, orderItem.getProductId());
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
		String sql = "DELETE FROM ORDER-ITEMS WHERE ID = ?";
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
	public OrderItem find(int id) {
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM ORDER-ITEMS" ;
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int quantity = rs.getInt("quantity");
				double price = rs.getDouble("price");
				int orderId = rs.getInt("orderid");
				int productId = rs.getInt("productid");
				
				return new OrderItem(quantity, price, orderId, productId);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<OrderItem> findAll() {
		// TODO Auto-generated method stub
		List<OrderItem> orList = new ArrayList<>();
		String sql = "SELECT * FROM CATEGORIES" ;
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				int quantity = rs.getInt("quantity");
				double price = rs.getDouble("price");
				int orderId = rs.getInt("orderid");
				int productId = rs.getInt("productid");
				
				orList.add(new OrderItem(quantity, price, orderId, productId));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return orList;
	}

	@Override
	public List<OrderItem> findByOder(int orderId) {
	List<OrderItem> orderItemList = new ArrayList<>();
        String sql = "SELECT * FROM ORDER_ITEMS WHERE orderid = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, orderId);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                int productId = rs.getInt("productid");
                orderItemList.add( new OrderItem(id, quantity, price, orderId, productId));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return orderItemList;
	}

	@Override
	public List<OrderItem> findByProduct(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
