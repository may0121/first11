<%--
  Created by IntelliJ IDEA.
  User: 86155
  Date: 2022/3/19
  Time: 14:19
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录页面</title>
    <script type="text/javascript">
        function noLook() {
            //验证码图片是一个内存图片,当第一次请求这个servlet生成验证码图片时,默认会把这个验证码的内存图片保存在服务端的内存中,再返回给客户端
            //当第二次用同样url请求这个servlet生成验证码图片时,服务器会直接将第一次生成验证码图片返回给客户端
            //如果想重新获得一个新验证码的内存图片,让每次请求的url不同
            document.getElementById("img1").src="/CheckCodeServlet?r="+new Date();
        }
    </script>
</head>
<body>
<%--<%--%>
<%--    //获得请求中登录失败信息--%>
<%--    Object ob= request.getAttribute("mess");--%>
<%--    if(ob!=null){--%>
<%--        out.write("<h1>"+ob+"</h1>");--%>
<%--    }--%>
<%--%>--%>

<%--&lt;%&ndash;获得记住的cookie中用户信息&ndash;%&gt;--%>
<%--<%--%>
<%--    //获得浏览器关于当前网站所有cookie--%>
<%--    Cookie[] cs=request.getCookies();--%>
<%--    //声明变量存cookie中记住的用户名和密码--%>
<%--    String uname2="";--%>
<%--    String upwd2="";--%>
<%--    if (cs!=null){--%>
<%--        //遍历cookie--%>
<%--        for(Cookie c:cs){--%>
<%--            //判断每个遍历的当前cookie的key名是否是登录时记住用户名和密码的key名--%>
<%--            if(c.getName().equals("uname1")){--%>
<%--                //如果当前cookie的key为uname1,说明当前cookie的value值就是登录成功时存的用户名--%>
<%--                uname2=c.getValue();--%>
<%--            }else if(c.getName().equals("upwd1")){--%>
<%--                upwd2=c.getValue();--%>
<%--            }--%>
<%--        }--%>
<%--    }--%>

<%--%>--%>
<!--获得请求中登录失败信息-->
<h1>${requestScope.mess}</h1>

<!--静态导入页面-->
<%@include file="logincookie.jsp"%>


<form method="post" action="/CommonServlet">
    <!--用隐藏域传请求标记参数-->
    <input type="hidden" name="method" value="login"/>
    <table>
        <tr>
            <td></td>
            <td>登录页面</td>
            <td></td>
        </tr>
        <tr>
            <td>用户名:</td>
            <td><input type="text" name="uname" id="uname" value="${uname2}"/></td>
            <td id="uname1"></td>
        </tr>
        <tr>
            <td>密码:</td>
            <td><input type="password" name="upassword" id="upassword" value="${upwd2}"/> </td>
            <td></td>
        </tr>
        <tr>
            <td>身份:</td>
            <td>
                <input type="radio" name="urole" value="student" checked="checked"/>普通用户
                <input type="radio" name="urole" value="admin"/>管理员
            </td>
            <td></td>
        </tr>
        <tr>
            <td>验证码:</td>
            <td>
                <input type="text" name="cdCode" />
                <img id="img1" src="/CheckCodeServlet" alt="图片加载失败"  onclick="noLook()"/> <a onclick="noLook()">看不清</a>
            </td>
            <td></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="checkbox" name="remberMe"/>记住我 </td>
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
