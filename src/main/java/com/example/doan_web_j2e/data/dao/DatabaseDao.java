package com.example.doan_web_j2e.data.dao;

public abstract class DatabaseDao {

    private static DatabaseDao instance;

    public static void init(DatabaseDao inst) {
        instance = inst;
    }

    public static DatabaseDao getInstance() {
        return instance;
    }

    public abstract ProductDao getProductDao();

    public abstract CategoryDao getCategoryDao();

    public abstract OrderItemDao getOrderItemDao();

    public abstract OrderDao getOrderDao();

    public abstract UserDAO getUserDao();

}
