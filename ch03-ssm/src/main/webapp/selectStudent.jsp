<%--
  Created by IntelliJ IDEA.
  User: 黄晨辉
  Date: 2021/1/27
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() + "://" +
            request.getServerName() + ":" + request.getServerPort() +
            request.getContextPath() + "/";
%>

<html>
<head>
    <script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
    <script type="text/javascript">
        $(function () {
            $("#btn").click(function () {
                $.ajax({
                    url : "student/selectStudent.do",
                    dataType : "json",
                    type : "get",
                    success : function (dada) {
                        $("#body").html("");
                        var html = "";
                        $.each(dada,function (i,n) {
                            html += '<tr>';
                            html += '<td>'+n.id+'</td>';
                            html += '<td>'+n.name+'</td>';
                            html += '<td>'+n.age+'</td>';
                            html += '</tr>';

                        })
                        $("#body").html(html);


                    }
                })

            })

        })
    </script>
    <title>Title</title>
    <base href="<%=basePath%>">
</head>
<body>
<div align="center">
    <table>
        <thead>
        <tr>
            <td>学号</td>
            <td>姓名</td>
            <td>年龄</td>
        </tr>


        </thead>
        <tbody id="body">



        </tbody>
    </table>
    <button id="btn">查找数据</button>
</div>

</body>
</html>
