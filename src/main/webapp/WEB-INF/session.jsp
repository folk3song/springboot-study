<%--
  Created by IntelliJ IDEA.
  User: frank
  Date: 2019/9/29
  Time: 10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Session</title>
</head>
<body>
<%
    session.setAttribute("id",1L);
    response.sendRedirect("./session/test");
%>
</body>
</html>
