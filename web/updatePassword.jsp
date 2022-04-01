<%--
  Created by IntelliJ IDEA.
  User: 86155
  Date: 2022/3/30
  Time: 12:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
<form action="/CommonServlet" method="post">
    <input type="hidden" name="method" value="updatePassword"/>
    姓名：<input type="text" name="uname" value="" ><br>
    新密码:<input type="password" name="upassword" value=""><br>
    <input  type="submit" value="提交">
</form>
</body>
</html>
