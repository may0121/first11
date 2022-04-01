<%--
  Created by IntelliJ IDEA.
  User: 86155
  Date: 2022/3/24
  Time: 12:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--获得记住的cookie中用户信息--%>
<%
    //获得浏览器关于当前网站所有cookie
    Cookie[] cs=request.getCookies();
    //声明变量存cookie中记住的用户名和密码
    String uname2="";
    String upwd2="";
    if (cs!=null){
        //遍历cookie
        for(Cookie c:cs){
            //判断每个遍历的当前cookie的key名是否是登录时记住用户名和密码的key名
            if(c.getName().equals("uname1")){
                //如果当前cookie的key为uname1,说明当前cookie的value值就是登录成功时存的用户名
                uname2=c.getValue();
            }else if(c.getName().equals("upwd1")){
                upwd2=c.getValue();
            }
        }
    }
    //将cookie获得记住用户名和密码存在请求范围内
    request.setAttribute("uname2",uname2);
    request.setAttribute("upwd2",upwd2);
%>
</body>
</html>

