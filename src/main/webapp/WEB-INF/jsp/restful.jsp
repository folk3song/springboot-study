<%--
  Created by IntelliJ IDEA.
  User: frank
  Date: 2019/9/29
  Time: 17:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>hello springboot</title>
    <script type="text/javascript" src = "https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script type="text/javascript">
        function post() {
            var params = {
                'userName':'user_name_new',
                'sexCode':1,
                'note':"note_new"
            }

            $.post({
                url : "./user",
                contentType:"application/json",
                data:JSON.stringify(params),
                success:function (result) {
                    if(result == null || result.id == null)
                    {
                        alert("插入失败");
                        return;
                    }
                    alert("插入成功");
                }

            });
        }
        function get(){
            $.get("./user/1",function (user,status) {
                if (user == null)
                {
                    alert("结果为空")
                }else {
                    alert("用户信息为"+JSON.stringify(user))
                }
            });
        }

        function postStatus()
        {
            var params = {
                'userName':"user_name_new",
                'sexCode':1,
                'note':'note_new'
            }

            var url = "./user2/annotation"
            $.post({
                url:url,
                contentType:"application/json",
                data:JSON.stringify(params),
                success:function (result,status,jqXHR) {
                    var success = jqXHR.getResponseHeader("success");
                    var status = jqXHR.status;
                    alert("响应头参数是："+success+",状态码是："+status);
                    if(result == null || result.id == null)
                    {
                        alert("插入失败");
                        return;
                    }
                    alert("插入成功")

                }
            });
        }
        postStatus()

    </script>
</head>
<body>
<h1>测试restful请求</h1>
</body>
</html>
