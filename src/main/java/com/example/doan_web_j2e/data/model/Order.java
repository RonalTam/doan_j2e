package com.example.doan_web_j2e.data.model;

import com.example.doan_web_j2e.data.dao.DatabaseDao;

import java.sql.Timestamp;

public class Order {

    private int id;
    private String code;
    private String status;
    private int userId;
    private Timestamp createdAt;
    private String fullname;
    private String phone;
    private String email;
    private String address;

    public static final String PENDING = "pending";
    public static final String FINISHED = "finish";

    public Order(String code, String status, int userId, String fullname, String phone, String email, String address) {
        super();
        this.code = code;
        this.status = status;
        this.userId = userId;
        this.fullname = fullname;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public Order(int id, String code, String status, int userId, Timestamp createdAt, String fullname, String phone, String email, String address) {
        super();
        this.id = id;
        this.code = code;
        this.status = status;
        this.userId = userId;
        this.createdAt = createdAt;
        this.fullname = fullname;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User getUser(){
        return DatabaseDao.getInstance().getUserDao().find(this.userId);
    }
}