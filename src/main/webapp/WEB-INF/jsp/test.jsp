<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>购买产品测试</title>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js">
    </script>
</head>

<script type="text/javascript">
    for(var i = 1;i<=1000;i++)
    {
        var params = {
            userId:1,
            productId:1,
            quantity:1
        };
        $.post("./purchase",params,function (result) {
            alert(result.message)
        });
    }



</script>

<body>
<h1>抢购产品测试</h1>
</body>
</html>