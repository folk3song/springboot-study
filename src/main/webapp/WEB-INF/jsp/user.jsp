<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Hello Spring Boot</title>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js">
    </script>

    <script type="text/javascript">
        function post(user) {
            var url = "./save";
            $.post(
                {
                    url: url,
                    contentType: "application/json",
                    data: JSON.stringify(user),
                    success: function (result, status) {
                        if (result == null || result.id == null) {
                            alert("插入失败");
                            return;
                        }
                    }
                }
            );
        }
        for (var i = 1;i<=10;i++)
        {
            var user = {
                'id':i,
                'userName':'user_name_'+i,
                'note':'note_'+i,
                'roles':[{
                    'id':i,
                    'roleName':'role_'+i,
                    'note':'note_'+i
                },{
                    'id':i+1,
                    'roleName':'role_1'+(i+1),
                    'note':'note_1'+(i+1)
                }]
            };
            post(user)
        }
    </script>
</head>
<body>
<h1>操作mongodb 文档</h1>
</body>
</html>