<%--
  Created by IntelliJ IDEA.
  User: frank
  Date: 2019/9/29
  Time: 9:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>文件上传</title>
</head>
<body>
<form method="post" action="./part" enctype="multipart/form-data">
    <input type = "file" name="file" value="请上传文件" />
    <input type="submit" value="提交"/>
</form>

</body>
</html>
