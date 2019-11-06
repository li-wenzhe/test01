<%--
  Created by IntelliJ IDEA.
  User: 456
  Date: 2019/10/15
  Time: 上午 10:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传</title>
</head>
<body>
<h1>传统方式上传文件</h1>
<form action="file/upload" method="post" enctype="multipart/form-data">
    图片： <input type="file" name="upload"/><br/>
    图片描述:<input type="text" name="pdesc"/><br>
    <input type="submit" value="上传"/>
</form>
</body>
</html>
