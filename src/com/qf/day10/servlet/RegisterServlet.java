package com.qf.day10.servlet;

import com.qf.day10.entity.Users;
import com.qf.day10.service.UsersService;
import com.qf.day10.service.impl.UsersServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //解决post 请求乱码的问题
        request.setCharacterEncoding("utf-8");
        Users u = new Users();
        u.setUname(request.getParameter("uname")) ;
        u.setUage(Integer.valueOf(request.getParameter("uage")));
        u.setUbirthday(request.getParameter("ubirthday"));
        u.setUcity(request.getParameter("ucity"));
        u.setUpassword(request.getParameter("upassword"));
        u.setUsex(request.getParameter("usex"));
        String[] hobbys = request.getParameterValues("uhobby");
        if (hobbys!=null){
            u.setUhobby(Arrays.toString(hobbys));
        }
        System.out.println("注册用户信息为："+u);
        //调用业务层处理请求
        UsersService usersService = new UsersServiceImpl();
        int result = 0;
        try {
            result = usersService.register(u);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        //响应一句话给浏览器客户端
        response.setContentType("text/html;charset=utf-8");

        if (result>0){
            response.getWriter().write("注册成功!");
            //5秒跳转
//            response.setHeader("refresh","5;url=login.html");
            //转发
            request.getRequestDispatcher("login.jsp").forward(request,response);
//            重定向
//            response.sendRedirect("login.html");
        }else {
            response.getWriter().write("注册失败!");
            //五秒跳转meta标签
//            response.setHeader("refresh","5;url=register.html");
            //转发
            request.getRequestDispatcher("register.jsp").forward(request,response);

            //重定向
//            response.sendRedirect("register.html");
        }




    }
}
