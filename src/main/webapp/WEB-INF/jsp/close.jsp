<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js">
    </script>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#submit").click(function (){
                $.post({
                    url:"./actuator/shutdown",
                    success:function (result) {
                        if(result != null || result.message != null)
                        {
                            alert(result.message);
                            return;
                        }

                        alert("关闭springboot应用失败")


                    }
                });

            });
        });

    </script>
    <title>测试关闭请求</title>
</head>
<body>
<input id = "submit" type = "button" value="关闭应用">
</body>
</html>