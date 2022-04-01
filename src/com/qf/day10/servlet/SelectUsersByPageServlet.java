package com.qf.day10.servlet;

import com.qf.day10.entity.Users;
import com.qf.day10.service.UsersService;
import com.qf.day10.service.impl.UsersServiceImpl;
import com.qf.day10.tool.PageTool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/SelectUsersByPageServlet")
public class SelectUsersByPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*1.接收语法中数据*/
//        //创建分页工具对象
//
//        //接收请求中页码,并存在分页工具对象的属性中
//        p.setCurrPage(Integer.valueOf(request.getParameter("curr")));

        /*2.调用业务层处理请求*/
        //创建用户业务对象
//        geUtil.setCurrPage(Integer.valueOf(request.getParameter("curr")));
        String curr = request.getParameter("curr");
        if (curr==null){
            curr="1";
        }
        PageTool p=new PageTool();
        p.setCurrPage(Integer.valueOf(curr));
        UsersService usersService=new UsersServiceImpl();
        //用业务对象调用方法处理请求并得到结果
        List<Users> usersList= null;
        try {
            usersList = usersService.selectUsersByPage(p);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        /*3.响应*/
        //将数据存在请求对象中带到下一个页面
        request.setAttribute("usersList",usersList);
        request.setAttribute("page",p);
        request.getRequestDispatcher("userList.jsp").forward(request,response);
    }
}
