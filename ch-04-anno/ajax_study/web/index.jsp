<%--
  Created by IntelliJ IDEA.
  User: 黄晨辉
  Date: 2020/11/5
  Time: 10:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <script type="text/javascript">
     function fun1() {
       //1.创建异步对象
       var xmlHttp = new XMLHttpRequest();
       //2.绑定事件
       xmlHttp.onreadystatechange = function () {
         //.处理服务端返回的数据
       if (xmlHttp.status == 200&&xmlHttp.readyState == 4){
         document.getElementById("div").innerText = xmlHttp.responseText;

       }




       }
       //3.初始请求参数
       //获取dom对象value值
       var name = document.getElementById("name").value;
       var h = document.getElementById("h").value;
       var w = document.getElementById("w").value;
       var parm = "name=" + name + "&h=" + h + "&w=" + w;
       alert(parm);
       xmlHttp.open("get","/myWeb/count?" + parm,true)

       //4.发起请求
       xmlHttp.send();


     }

    </script>
    <title>$Title$</title>
  </head>
  <body>
  <center>
  <input type="text" id="name">姓名<br>
  <input type="text" id="h">身高<br>
  <input type="text" id="w">体重<br>
  <input type="button" value="计算bim" onclick="fun1()">
    <div id="div" style="color: red;background-color: aliceblue;"></div>
    </center>
  </body>
</html>
