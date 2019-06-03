<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注册</title>
<link rel="stylesheet" href="./css/reg.css">
</head>
<body>
    <div class="reg">
        <div class="header">
            <h1>
                <a href="./login.jsp">登录</a> <a href="./regist.jsp">注册</a>
            </h1>
        </div>
        <form action="registServlet" method="post">
            <table>
                <tr>
                    <td class="td1">账号</td>
                    <td><input type="text" class="input1" pattern="[0-9]{3,6}" placeholder="请输入正确的账号！" name="account"></td>
                </tr>
                <tr>
                    <td class="td1">密码</td>
                    <td><input type="password" class="input1" pattern="[0-9]{6,}" placeholder="请输入正确的密码！" name="password"></td>
                </tr>
                <tr>
                    <td class="td1">姓名</td>
                    <td><input type="text" class="input1" pattern="[a-zA-Z]{3,6}" placeholder="请输入正确的姓名" name="name"></td>
                </tr>
                <tr>
                    <td class="td1">邮箱</td>
                    <td>
                        <input type="email" class="input1" name="email">
                    </td>
                </tr>
                <tr>
                    <td class="td1">性别</td>
                    <td>
                        <input type="radio" name="sex" value="male">男
                        <input type="radio" name="sex" value="female">女
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <div class="btn-red">
                            <input type="submit" value="注册" id="reg-btn">
                        </div>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>