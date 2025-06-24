package com.example.doan_web_j2e.data.dao;

import com.example.doan_web_j2e.data.model.Category;

import java.util.List;

public interface CategoryDao {
	public boolean insert(Category category);
	public boolean update(Category category);
	public boolean delete(int id);
	public Category find(int id);
	public List<Category> findAll();
	public List<Category> hotCategory();
}
