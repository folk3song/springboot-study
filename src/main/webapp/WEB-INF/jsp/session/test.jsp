<%@ page import="com.springboot.chapter9.pojo.User" %><%--
  Created by IntelliJ IDEA.
  User: frank
  Date: 2019/9/29
  Time: 10:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试@sessionAttributes</title>
</head>
<body>
<%
    User user = (User) session.getAttribute("user");
    Long id = (Long) session.getAttribute("id_new");
    System.out.println("<br> username = " + user.getUserName());
    System.out.println("<br> id_name = "+id);
%>
</body>
</html>
