package com.Servlet.User.servlet;

import com.Servlet.User.domain.User;
import com.Servlet.User.mysql.Jdbc;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/registServlet")
public class registServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String account = request.getParameter("account");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String sex = request.getParameter("sex");

        User newUser = new User(account, name, password, email, sex);
        if (Jdbc.setMysql(newUser) != 0) {
            request.getRequestDispatcher("/registSuccess.jsp").forward(request,response);
        } else {
            request.getRequestDispatcher("/failRegist.jsp").forward(request,response);
        }
        //request.setAttribute("user", newUser);
        //List<User> userList =  (List<User>) this.getServletContext().getAttribute("list");
        //System.out.println(userList.get(0).getAccount());
        //this.getServletContext().setAttribute("list",userList);
    }
}
