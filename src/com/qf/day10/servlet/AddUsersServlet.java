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

@WebServlet("/AddUsersServlet")
public class AddUsersServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        UsersService service = new UsersServiceImpl();
        int i = 0;
        try {
            i = service.addUsers(u);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        //响应
        response.setContentType("text/html;charset=utf-8");
        if (i>0){

            response.getWriter().write("注册成功");
//           没有数据   request.getRequestDispatcher("userList.jsp").forward(request,response);
            response.sendRedirect(request.getContextPath()+"/SelectUsersByPageServlet");
        }else {
            response.getWriter().write("注册失败");
            request.getRequestDispatcher("/userList.jsp").forward(request,response);
//            response.sendRedirect(request.getContextPath()+"/AddUsersServlet");
        }
    }
}
