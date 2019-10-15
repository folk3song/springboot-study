<%--
  Created by IntelliJ IDEA.
  User: frank
  Date: 2019/9/27
  Time: 16:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>测试 jsr-303</title>
    <script src="https://code.jquery.com/jquery-3.2.0.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            var pojo = {
                id:null,
                date:'2017-08-08',
                doubleValue:999999.09,
                integer:100,
                range:1000,
                email:'email',
                size:'adv1212',
                regexp:'a,b,c,d'
            }
            $.post({
                url:"./validate",
                contentType:"application/json",
                data:JSON.stringify(pojo),
                success:function (result) {

                }
            });

        });
    </script>
</head>
<body>

</body>
</html>
