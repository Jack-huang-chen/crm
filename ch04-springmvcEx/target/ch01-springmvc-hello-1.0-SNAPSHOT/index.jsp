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
<a href="user/some.do?name=黄晨辉&age=22">点击doSome</a>
<nbsp></nbsp>
<form action="user/other.do" method="post">
      名字<input type="text" name="name">
      年龄<input type="text" name="age">
    <input type="submit" value="提交">
</form>
<br>

<form action="user/param.do" method="post">
    名字<input type="text" name="name">
    年龄<input type="text" name="age">
    <input type="submit" value="对象提交">
</form>

<form action="user/param.do" method="post">
    名字<input id="name" type="text" name="name">
    年龄<input  id="age" type="text" name="age">

</form>
<button type="button" id="sub">发起ajax请求</button>
<br>
<button type="button" id="stringBtn">返回文本</button>
</body>
</html>
