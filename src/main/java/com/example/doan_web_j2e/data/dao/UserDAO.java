package com.example.doan_web_j2e.data.dao;

import com.example.doan_web_j2e.data.model.User;

import java.util.List;

public interface UserDAO {

    public boolean insert(User user);

    public boolean update(User user);

    public boolean delete(int userId);

    public User find(int id);

    public List<User> findAll();

    public User find(String email, String password);

    public User find(String email);
}
