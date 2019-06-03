package com.Servlet.User.servlet;

import com.Servlet.User.domain.User;
import com.Servlet.User.mysql.Jdbc;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.util.ArrayList;
import java.util.List;

import static com.Servlet.User.mysql.Jdbc.loadMysql;


//@WebServlet("/initServlet")
public class initServlet extends javax.servlet.http.HttpServlet {
    public void init() throws ServletException {
        List<User> list = new ArrayList<>();
        this.getServletContext().setAttribute("list", list);
    }
}
