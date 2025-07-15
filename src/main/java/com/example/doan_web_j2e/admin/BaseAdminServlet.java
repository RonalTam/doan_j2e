/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.doan_web_j2e.admin;

import com.example.doan_web_j2e.data.dao.Database;
import com.example.doan_web_j2e.data.dao.DatabaseDao;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;

/**
 *
 * @author Welcome
 */
public class BaseAdminServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        DatabaseDao.init(new Database());
    }
    
}
