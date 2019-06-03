package com.Servlet.User.domain;

import java.util.List;

public class User {
    private String account;
    private String name;
    private String password;
    private String email;
    private String sex;

    public User() {

    }

    public User(String account, String name, String password,String email, String sex) {

        this.account = account;
        this.name = name;
        this.password = password;
        this.email = email;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getSex() {
        return sex;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

}
