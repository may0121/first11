package com.qf.day10.servlet;

import com.qf.day10.service.UsersService;
import com.qf.day10.service.impl.UsersServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/delUsersServlet")
public class delUsersServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");

        String uid = request.getParameter("uid");
        UsersServiceImpl service = new UsersServiceImpl();
        try {
            int i = service.delUsers(uid);
            if (i>0){
                response.getWriter().write("用户删除成功");
                response.getWriter().write("用户删除成功");
            }else {
                response.getWriter().write("用户删除失败");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


//        response.sendRedirect(request.getContextPath()+"/SelectUsersByPageServlet");

    }
}
