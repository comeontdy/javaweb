package com.Servlet.User.mysql;

import com.Servlet.User.domain.User;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import sun.security.provider.MD5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.*;

public class Jdbc {
    public static List<User> list = new ArrayList<>();
    static String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static String DB_URL = "jdbc:mysql://localhost:3306/shujuku?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
    static String USER = "root";
    static String PASSWORD = "123456";

    static {
        list.add(new User("000","AAA","123456","AAA@qq.com","male"));
        list.add(new User("001","BBB","123456","BBB@qq.com","female"));
        list.add(new User("002","CCC","123456","CCC@qq.com","female"));
        list.add(new User("003","DDD","123456","DDD@qq.com","male"));
        list.add(new User("004","EEE","123456","EEE@qq.com","female"));
        list.add(new User("005","FFF","123456","FFF@qq.com","male"));
        list.add(new User("006","GGG","123456","GGG@qq.com","male"));
        list.add(new User("007","HHH","123456","HHH@qq.com","female"));
        list.add(new User("008","III","123456","III@qq.com","female"));
        list.add(new User("009","JJJ","123456","JJJ@qq.com","male"));
        list.add(new User("010","KKK","123456","KKK@qq.com","female"));
        list.add(new User("011","LLL","123456","LLL@qq.com","male"));
    }
    public static void main(String[] args) {
//        String data = "123456";
//        String pass = md5(data);
//        for (User u:loadAllData()) {
//            if (u.getPassword().equals(pass))
//                System.out.println(u.getAccount());
//        }
//        int i = 0;
//        for (User u:list){
//            i = setMysql(u);
//            System.out.println(i);
//        }
    }

    public static List<User> loadMysql(int page) {
        List<User> list = new ArrayList<>();
        Connection con = null;
        Statement stmt = null;
        int limitPage = 5;
        try {
            Class.forName(JDBC_DRIVER);

            //DB_URL = "jdbc:mysql://localhost:3306/mydatabase?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
            DB_URL = "jdbc:mysql://localhost:3306/mydatabase?useUnicode=true&characterEncoding=UTF-8&useSSL=true&serverTimezone=GMT";
            con = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            stmt = con.createStatement();

            //ResultSet rs = stmt.executeQuery("SELECT account , name , password , email,sex FROM test");
            String limitStr = "select * from user limit ?,?";
            PreparedStatement ps = con.prepareStatement(limitStr);
            ps.setInt(1,(page - 1)*limitPage);
            ps.setInt(2,limitPage);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String account = rs.getString("account");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String sex = rs.getString("sex");
                //map.put(account, new User(name, account, password, email));
                list.add(new User(account, name, password, email, sex));
//                System.out.print("account: " + account);
//                System.out.print(",姓名: " + name);
//                System.out.print(",email: " + email);
//                System.out.print(",sex: " + sex);
//                System.out.print("\n");
            }
            ps.close();
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException se){
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try{
                if(stmt != null)
                    stmt.close();
            }catch (SQLException e){
            }
            try{
                if (con != null)
                    con.close();
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public static List<User> loadAllData() {
        List<User> list = new ArrayList<>();
        try {
            DB_URL = "jdbc:mysql://localhost:3306/mydatabase?useUnicode=true&characterEncoding=UTF-8&useSSL=true&serverTimezone=GMT";
            Connection con = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            String allData = "select * from user ";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(allData);
            while (rs.next()) {
                String account = rs.getString("account");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String sex = rs.getString("sex");
                list.add(new User(account, name, password, email, sex));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static int setMysql(User u) {
        int tag = 0;
        int flag = 1;
        try {
            DB_URL = "jdbc:mysql://localhost:3306/mydatabase?useUnicode=true&characterEncoding=UTF-8&useSSL=true&serverTimezone=GMT";
            Connection con = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            String selectData = "select * from user ";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(selectData);
            while (rs.next()) {
                String account = rs.getString("account");
                String password = rs.getString("password");
                if (account.equals(u.getAccount()) && password.equals(u.getPassword())) {
                    flag = 0;
                    return flag;
                }
            }

            if (flag == 1) {
                String insertData = "insert into user(account, name, password , email,sex ) values(?,?,MD5(?),?,?)";
                PreparedStatement ps = con.prepareStatement(insertData);
                ps.setString(1, u.getAccount());
                ps.setString(2, u.getName());
                ps.setString(3, u.getPassword());
                ps.setString(4, u.getEmail());
                ps.setString(5, u.getSex());
//                ps.setString(3, u.getEmail());
//                ps.setString(4, u.getSex());

                tag = ps.executeUpdate();
                ps.close();
            }

            con.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tag;
    }

    public static int deleteMysql(User u) {
        int flag = 0;
        try {
            DB_URL = "jdbc:mysql://localhost:3306/mydatabase?useUnicode=true&characterEncoding=UTF-8&useSSL=true&serverTimezone=GMT";
            Connection con = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            String deleteData = "delete from user where account='" + u.getAccount() + "'";
            PreparedStatement stmt = con.prepareStatement(deleteData);
            flag = stmt.executeUpdate();
            con.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static String md5(String data) {
        StringBuffer buf = new StringBuffer();
        try{
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(data.getBytes());
            byte[] bits = md.digest();
            for(int i=0;i<bits.length;i++) {
                int a = bits[i];
                if (a < 0) a += 256;
                if (a < 16) buf.append("0");
                buf.append(Integer.toHexString(a));
            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return buf.toString();
    }
}
