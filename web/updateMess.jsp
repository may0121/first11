<%--
  Created by IntelliJ IDEA.
  User: 86155
  Date: 2022/3/30
  Time: 15:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="/UsersServlet">

    <!--    用隐藏域传请求的标记参数-->

    <!--    用隐藏域传请求的标记参数   ，先通过show方法传递展示 修改用户原有的信息，然后通过update 方法  在原有信息基础上修改-->

    <input type="hidden" name="method" value="updateMess"/>
<%--    <input type="">--%>
    <table>
        <tr>
            <td></td>
            <td>修改用户信息</td>
        </tr>
        <tr>
            <td>用户名:</td>

<%--            这里面的参数也要变成loginUser.属性  因为值存在loginUser当中，修改后的值也在loginUser 当中--%>

            <td><input type="text" name="uname" id="uname " value="${loginUser.uname}"/></td>
            <td id="uname1"></td>
        </tr>

        <tr>
            <td>年龄:</td>
            <td><input type="number" name="uage" id="uage" value="${loginUser.uage}"/> </td>
            <td></td>
        </tr>
        <tr>
            <td>性别:</td>
            <td>
                <input type="radio" name="usex" value="man" <c:if test="${loginUser.usex=='man'}">checked="checked"</c:if>/>男
                <input type="radio" name="usex" value="woman" <c:if test="${loginUser.usex=='woman'}">checked="checked"</c:if>/>女
            </td>
            <td></td>
        </tr>
        <tr>
            <td>爱好:</td>
            <td>
                <input type="checkbox" name="uhobby" value="sing"<c:if test="${fn:contains(loginUser.uhobby,'sing')}">checked="checked"</c:if>/>唱歌
                <input type="checkbox" name="uhobby" value="dancing"<c:if test="${fn:contains(loginUser.uhobby,'dancing')}">checked="checked"</c:if>/>跳舞
                <input type="checkbox" name="uhobby" value="rap"<c:if test="${fn:contains(loginUser.uhobby,'rap')}">checked="checked"</c:if>/>rap
                <input type="checkbox" name="uhobby" value="game"<c:if test="${fn:contains(loginUser.uhobby,'game')}">checked="checked"</c:if>/>游戏
            </td>
            <td></td>
        </tr>
        <tr>
            <td>所在城市:</td>
            <td>
                <select name="ucity">
                    <option value="beijing"<c:if test="${loginUser.ucity=='beijing'}">selected="selected"</c:if>>北京</option>
                    <option value="shanghai"<c:if test="${loginUser.ucity=='shanghai'}">selected="selected"</c:if>>上海</option>
                    <option value="shenzhen"<c:if test="${loginUser.ucity=='shenzhen'}">selected="selected"</c:if>>深圳</option>
                    <option value="guangzhou"<c:if test="${loginUser.ucity=='guangzhou'}">selected="selected"</c:if>>广州</option>
                    <option value="shangrao"<c:if test="${loginUser.ucity=='shangrao'}">selected="selected"</c:if>>上饶</option>
                </select>
            </td>
            <td></td>
        </tr>
        <tr>
            <td>出生日期:</td>
            <td>
                <input type="date" name="ubirthday" value="${loginUser.ubirthday}"/>
            </td>
            <td></td>
        </tr>
        <tr>
            <td>协议:</td>
            <td>
                        <textarea rows="3" cols="20">
                            请阅读以下协议:
                                dfkeifiefeidifrfeifeio[dkk[dfrfreif
                        </textarea>
            </td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td>
                <input type="submit" value="提交"/>
                <input type="reset" value="重置"/>
            </td>
            <td></td>
        </tr>

    </table>
</form>
</body>
</html>
