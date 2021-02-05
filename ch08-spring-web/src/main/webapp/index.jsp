<%--
  Created by IntelliJ IDEA.
  User: 黄晨辉
  Date: 2021/1/21
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <p>注册商品</p>
    <form action="login" method="post">
        <table>
            <tr>
                <td>商品名</td>
                <td><input type="text" name="name"></td>
            </tr>
            <tr>
                <td>商品数量</td>
                <td><input type="text" name="amount"></td>
            </tr>
            <tr>
                <td>价格</td>
                <td><input type="text" name="price"></td>
            </tr>
            <tr>

                <td><input type="submit" value="注册"></td>
            </tr>
        </table>


    </form>
</body>
</html>
