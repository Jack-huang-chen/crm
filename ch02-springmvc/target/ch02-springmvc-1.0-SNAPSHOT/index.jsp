<%--
  Created by IntelliJ IDEA.
  User: 黄晨辉
  Date: 2021/1/23
  Time: 11:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#sub").click(function () {
                var name =$("#name").val();
                var age = $("#age").val();
                $.ajax({
                        url : "user/ajax.do",
                        dataType : "json",
                        type : "post",
                        data : {
                            "name" : name,
                            "age" : age
                        },
                        success : function (resp) {
                            alert(resp.name + "=====" + resp.age);

                        }
                    }

                )


            })
            $("#stringBtn").click(function () {
                $.ajax({
                    url: "user/string.do",
                    data: {
                        "name":"黄",
                        "age":"22"

                    },
                    type: "post",
                    success : function (resp) {

                        alert(resp);

                    }

                })

            })

        })

    </script>
</head>
<body>
<div align="center">
    <form action="user/login.do">
        <table>
            <tr>
                <td>姓名</td>
                <td><input type="text" name="name"></td>
            </tr>
            <tr>
                <td><input type="submit" value="登录"></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
