package com.example.doan_web_j2e.data.dao;

import com.example.doan_web_j2e.data.model.Product;

import java.util.List;

public interface ProductDao {

    public int insert(Product product);

    public boolean update(Product product);

    public boolean delete(int id);

    public Product find(int id);

    public List<Product> findAll();

    public List<Product> hot(int limit);

    public List<Product> news(int limit);

    public List<Product> findByCategory(int id);

    public List<Product> findByName(String key);

    public List<Product> findByCategoryOfName(int categoryId, String key);
    
    public List<Product> filter(int categoryId, String propertyName, String order);

    public List<Product> relatedProductList(Product product);

    public List<Product> getProducts(int offset, int limit);

    public int countAll();

    public int countByCategory(int categoryId);

    public List<Product> getProductsByCategory(int categoryId, int offset, int limit);
    
    public boolean updateView(Product product);

}
