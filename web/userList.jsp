<!--
  Created by IntelliJ IDEA.
  User: 86155
  Date: 2022/3/24
  Time: 12:14
  To change this template use File | Settings | File Templates.
-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--修改成功的提示语--%>
<h1>${mess}</h1>
<table border="1">
    <tr>
        <td>编号</td>
        <td>用户名</td>
        <td>年龄</td>
        <td>性别</td>
        <td>爱好</td>
        <td>所在城市</td>
        <td>出生日期</td>
        <td>操作</td>
    </tr>
    <c:forEach var="u" items="${requestScope.usersList}">
        <tr>
            <td>${u.uid}</td>
            <td>${u.uname}</td>
            <td>${u.uage}</td>
            <td>${u.usex}</td>
            <td>${u.uhobby}</td>
            <td>${u.ucity}</td>
            <td>${u.ubirthday}</td>
            <td>
                <a  href="/UsersServlet?method=selectUpdateUserById&uid=${u.uid}">修改</a>

                <a class="btn btn-default btn-sm" href="javascript:deletUsers(${u.uid});">删除</a>
                <script>
                    function deletUsers(uid) {
                        if (confirm("你确定删除吗？")){
                            location.href = "${pageContext.request.contextPath}/delUsersServlet?uid="+uid;
                        }
                    }
                </script>
            </td>
        </tr>
    </c:forEach>
    <tr>
        <td colspan="8">
            <c:if test="${requestScope.page.currPage>1}">
                <a href="UsersServlet?method=selectByPage&curr=${requestScope.page.currPage-1}"><input type="button" value="上一页"/></a>
            </c:if>
            当前是第${requestScope.page.currPage}页,
            总共${requestScope.page.totalPage}页
            <c:if test="${requestScope.page.currPage<requestScope.page.totalPage}">
                <a href="UsersServlet?method=selectByPage&curr=${requestScope.page.currPage+1}"><input type="button" value="下一页"/></a>
            </c:if>
        </td>
    </tr>
    <tr>
        <td colspan="8">
            <c:forEach begin="1" step="1" end="${requestScope.page.totalPage}" varStatus="status">
                <a href="UsersServlet?method=selectByPage&curr=${status.index}">${status.index}</a>
            </c:forEach>
        </td>
    </tr>

</table>
<a href="addUsers.html">添加用户信息</a>
</body>
</html>
