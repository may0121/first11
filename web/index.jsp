<%@ page import="com.qf.day10.entity.Users" %><%--
  Created by IntelliJ IDEA.
  User: 86155
  Date: 2022/3/17
  Time: 9:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  <head>
    <title>Title</title>
  </head>
  <body>
<h1>首页</h1>
  <h2>欢迎,${sessionScope.loginUser.uname}</h2>
<c:choose>
  <c:when test="${sessionScope.loginUser.urole=='student'}">
    <h2>
      <a href="login.jsp">退出</a><br/>

<%--      <a href="updatePassword.jsp">修改密码</a><br/>--%>

      <a href="CommonServlet?method=selectPassByName&uname=${sessionScope.loginUser.uname}">修改密码</a><br/>
<%--      这里的参数name获取  后面输入的uname   点击这个页面就跳转到 show方法，然后值会存在loginUser当中--%>

      <a href="/UsersServlet?method=show&name=${sessionScope.loginUser.uname}">修改用户信息</a><br/>
    </h2>
  </c:when>
  <c:when test="${sessionScope.loginUser.urole=='admin'}">
    <h2>

<%--      <a href="updatePassword.jsp">修改密码</a><br/>--%>

    <a href="CommonServlet?method=selectPassByName&uname=${sessionScope.loginUser.uname}">修改密码</a><br/>

      <a href="UsersServlet?method=selectByPage&curr=1">管理普通用户信息</a><br/>
    </h2>
  </c:when>
  <c:otherwise>
    <h1>你还没有登录,5秒后跳转到登录页面!</h1>
    <meta http-equiv="refresh" content="5;url=login.jsp"/>
  </c:otherwise>
</c:choose>
  </body>
</html>
