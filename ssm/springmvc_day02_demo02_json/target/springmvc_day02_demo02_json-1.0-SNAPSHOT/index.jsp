
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>index</title>


</head>

<script src="js/jquery-3.3.1.min.js"></script>
<body>

<h1>三,响应json</h1>
<input id="btnId" type="button" value="发送Ajax请求"/><br/>

<script>
    alert("666666");
    $(function () {
        $("#btnId").click(function () {
            $.post("response/json",{name:"zs",age:18},function (result) {
                alert("result="+result.name);
            },"json");
        });
    });
</script>
</body>
</html>
