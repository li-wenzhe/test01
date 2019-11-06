<%--
  Created by IntelliJ IDEA.
  User: 456
  Date: 2019/09/07
  Time: 下午 04:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>发帖</title>
  </head>
  <body>
  <form action="${pageContext.request.contextPath}/bbs" method="post">
    <textarea name="content" id="" cols="100" rows="10"></textarea>
    <br>
    <input type="submit" value="提交">
  </form>
  </body>
</html>
