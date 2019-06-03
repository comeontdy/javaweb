package com.Servlet.User.servlet;

import com.Servlet.User.domain.User;
import com.Servlet.User.mysql.Jdbc;
import sun.nio.cs.US_ASCII;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

//import static com.Servlet.User.mysql.Jdbc.loadSql;

@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int currPage = 1;
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        String sb = request.getParameter("page");
        List<User> allList = new ArrayList<>();
        allList = Jdbc.loadAllData();

        int maxPage = allList.size()/5 + 1;
        //System.out.println(sb);
        if(request.getParameter("page")!=null){
            currPage = Integer.parseInt(request.getParameter("page"));
        }
        //System.out.println(currPage);

        List<User> tempList = new ArrayList<>();
        StringBuilder nextPage = new StringBuilder();
        StringBuilder prePage = new StringBuilder();
        int tempCurrPage = currPage;

        if(currPage > 0 && currPage <= maxPage) {
            tempList = Jdbc.loadMysql(currPage);
            nextPage.append("<a style='float:right' href='loginServlet?page="+(++currPage)+"'>"+"下一页"+"</a>");
            prePage.append("<a style='float:right' href='loginServlet?page=" +(--tempCurrPage)+"'>"+"上一页"+"</a>");
        }else if(currPage <= 1){
            tempList = null;
            nextPage.append("<a style='float:right' href='loginServlet?page="+(++currPage)+"'>"+"下一页"+"</a>");
            prePage.append("<a style='float:right' href='loginServlet?page=" +(1)+"'>"+"上一页"+"</a>");
            List<User> judgeList = Jdbc.loadMysql(1);
            String tag = "first";
            request.setAttribute("tag", tag);
            request.setAttribute("judge", judgeList);
        }else {
            tempList = null;
            nextPage.append("<a style='float:right' href='loginServlet?page="+(maxPage)+"'>"+"下一页"+"</a>");
            prePage.append("<a style='float:right' href='loginServlet?page=" +(--tempCurrPage)+"'>"+"上一页"+"</a>");
            List<User> judgeList = Jdbc.loadMysql(maxPage);
            String tag = "last";
            request.setAttribute("tag", tag);
            request.setAttribute("judge", judgeList);
        }

        request.setAttribute("nextPage",nextPage.toString());
        request.setAttribute("prePage", prePage.toString());
        request.setAttribute("list", tempList);
        //request.getRequestDispatcher("/success.jsp").forward(request, response);
        //String pass = Jdbc.md5("123456");

        if (account != null) {
            for (User u : allList) {
                //System.out.println(u.getAccount()+" "+u.getName());
                if (account.equals(u.getAccount())) {
                    if (Jdbc.md5(password).equals(u.getPassword())) {
                        request.getRequestDispatcher("/success.jsp").forward(request, response);
                        break;
                    } else {
                        request.getRequestDispatcher("/failLogin.jsp").forward(request, response);
                        break;
                    }
                }
            }
        }else {
            request.getRequestDispatcher("/success.jsp").forward(request, response);
        }
    }
}
