<%@ page import="java.util.List" %>
<%@ page import="com.Servlet.User.domain.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登录成功</title>
<link rel="stylesheet" href="./css/login.css">
</head>
<body>
	<div class="login">
		<div class="header">
			<h1>欢迎来到账号管理系统</h1>
			<%= request.getAttribute("nextPage") %>
			<%= request.getAttribute("prePage") %>
		</div>
		<hr>
		<div class="content">
			<table align="center" border="1" width="450px">
				<tr>
					<td>account</td>
					<td>name</td>
					<td>email</td>
					<td>sex</td>
				</tr>
				<%
					List<User> list = (List<User>) request.getAttribute("list");
					if(list != null) {
                        for (User u : list) {
                            %>
                                <tr>
                                    <td><%= u.getAccount() %></td>
                                    <td><%= u.getName() %></td>
                                    <td><%= u.getEmail() %></td>
                                    <td><%= u.getSex() %></td>
                                </tr>
                            <%
                        }
                    }else{
					    String tag = (String)request.getAttribute("tag");
					    if (tag == "first") {
                            out.print("<script>alert('已是第一页'); </script>");
                            List<User> judgeList = (List<User>) request.getAttribute("judge");
                            for (User u : judgeList) {
                            %>
                                <tr>
                                    <td><%= u.getAccount() %></td>
                                    <td><%= u.getName() %></td>
                                    <td><%= u.getEmail() %></td>
                                    <td><%= u.getSex() %></td>
                                </tr>
                            <%
                            }
                        }else {
                                out.print("<script>alert('已是最后一页'); </script>");
                                List<User> judgeList = (List<User>) request.getAttribute("judge");
                                for (User u : judgeList) {
                                %>
                                <tr>
                                    <td><%= u.getAccount() %></td>
                                    <td><%= u.getName() %></td>
                                    <td><%= u.getEmail() %></td>
                                    <td><%= u.getSex() %></td>
                                </tr>
                            <%
                            }
					    }
					}
				%>
			</table>
		</div>
        <div>
            <tr style="float: end">
                <td style="float: right">
                    <a style="float: right" href="./login.jsp">退出</a>
                </td>
            </tr>
        </div>
	</div>
</body>
</html>