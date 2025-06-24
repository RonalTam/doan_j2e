package com.example.doan_web_j2e.data.dao;

import com.example.doan_web_j2e.data.model.OrderItem;

import java.util.List;

public interface OrderItemDao {

    public boolean insert(OrderItem orderItem);

    public boolean update(OrderItem orderItem);

    public boolean delete(int id);

    public OrderItem find(int id);

    public List<OrderItem> findAll();

    public List<OrderItem> findByOder(int id);

    public List<OrderItem> findByProduct(int id);

}
