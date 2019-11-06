<%--
  Created by IntelliJ IDEA.
  User: 456
  Date: 2019/10/14
  Time: 上午 09:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello SpringMVC</title>
</head>
<body>
<a href="hello">跳转Hello</a><br>
<br>
<br>
<br>
<h1>基本类型和String类型</h1>
<a href="list?name=zs&money=500.0">基本类型和String类型</a><br><br><br><br>
<form action="add33" method="post">
    账户ID:<input type="text" name="id"/><br>
    账户名称:<input type="text" name="name"/><br>
    账户金额:<input type="text" name="money"/><br>
    <input type="submit">
</form><br><br><br><br>
<form action="update33" method="post">
    账户ID:<input type="text" name="id"/><br>
    账户名称:<input type="text" name="name"/><br>
    账户金额:<input type="text" name="money"/><br>
    账户省份:<input type="text" name="address.provinceName"/><br>
    账户城市:<input type="text" name="address.cityName"/><br>
    <input type="submit">
</form><br><br><br><br>
</body>
</html>
