package com.Servlet.User.domain;

import com.Servlet.User.mysql.Jdbc;

import java.util.List;

public class test {
    public static void main(String[] args) {
        List<User> list = Jdbc.list;
        for(User u : list) {
            System.out.print("account: " + u.getAccount());
            System.out.print(",姓名: " + u.getName());
            System.out.print(",email: " + u.getEmail());
            System.out.print(",sex: " + u.getSex());
            System.out.print("\n");
        }
    }
}
