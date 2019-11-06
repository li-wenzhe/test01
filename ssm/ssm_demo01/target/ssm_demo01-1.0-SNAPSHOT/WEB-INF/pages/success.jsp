<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<center>
    <h1>账户信息</h1>
    <table border="1px" width="500px" border="1px">
        <tr>
            <td>账户Id</td>
            <td>账户姓名</td>
            <td>金额</td>
        </tr>
        <c:forEach items="${list}" var="account">
            <tr>
                <td>${account.id}</td>
                <td>${account.name}</td>
                <td>${account.money}</td>
            </tr>
        </c:forEach>
    </table>
</center>
</body>
</html>
