<%--
  Created by IntelliJ IDEA.
  User: 黄晨辉
  Date: 2021/1/27
  Time: 11:41
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
    <title>Title</title>
    <base href="<%=basePath%>">
</head>
<body>
<div align="center">
    <p>整合例子</p>
<table>
    <tr>
        <td><a href="addStudent.jsp">注册学生</a></td>
    </tr>
    <tr>
        <td><a href="selectStudent.jsp">查找学生</a></td>
    </tr>
</table>

</div>

</body>
</html>
