package com.qf.day10.servlet;

import com.qf.day10.Vo.LoginVo;

import com.qf.day10.entity.Users;
import com.qf.day10.service.UsersService;
import com.qf.day10.service.impl.UsersServiceImpl;
import com.qf.day10.tool.PageTool;


import  javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/UsersServlet")
public class UsersServlet extends FatherServlet {
    //根据编号修改用户信息
    public String updateUserByUid (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

      Users u = this.getRequestParams(Users.class,request);
      UsersService usersService = new UsersServiceImpl();
       int result = 0;
        try {
            result = usersService.updateUserByUid(u);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //响应
        request.setAttribute("mess","修改成功！");
        return "dispatcher:UsersServlet?method=selectByPage&curr=1";
    }






    //根据编号查询要修改的用户信息
    public String selectUpdateUserById (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
//接收请求中的数据  m默认字符串类型
        String uid = request.getParameter("uid");
        //调用业务层处理
        UsersService usersService = new UsersServiceImpl();
        Users user= null;
        try {
            user = usersService.selectUpdateUserById(uid);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //响应
        request.setAttribute("u",user);
        return "dispatcher:updateUser.jsp";
    }

    public String updateMess (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
 //得到当前对象的所有属性值

        Users user = this.getRequestParams(Users.class,request);
        UsersService usersService = new UsersServiceImpl();


//        request.getSession().setAttribute("upassword", user.getUpassword());
//        String upassword = u.getUname();
//        Users users = null;

        int result = 0;
        try {



            result = usersService.updateMess(user);
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
if (result>0){
    request.setAttribute("mess","修改成功！");
    return "dispatcher:login.jsp";

}else {
    return "dispatcher:register.html";
}
        //响应

    }

    //展示用户信息
    public String  show (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
//
//        String name = request.getParameter("name");
//        UsersService usersService = new UsersServiceImpl();
//       Users u= usersService.selectUserByUname(name);

        //前端页面里面的name 然后返回得到他的值
        String name = request.getParameter("name");
        //调业务层处理请求
        UsersService usersService = new UsersServiceImpl();
        //selectUserByUname   业务层中创造方法 以名字为参数传进去
        Users u= usersService.selectUserByUname(name);
        //将得到的值存在对象u当中，其中loginUser 是key

        request.setAttribute("loginUser",u);
        return "dispatcher:updateMess.jsp";
      }


    //根据编号查询要修改的用户信息
//public String selectUpdateUserById (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
////接收请求中的数据  m默认字符串类型
//    String uid = request.getParameter("uid");
//    //调用业务层处理
//    UsersService usersService = new UsersServiceImpl();
//    Users user= null;
//    try {
//        user = usersService.selectUpdateUserById(uid);
//    } catch (SQLException throwables) {
//        throwables.printStackTrace();
//    }
//
//    //响应
//    request.setAttribute("u",user);
//    return "dispatcher:updateUser.jsp";
//}


//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//doGet(request,response);
//    }
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String method = request.getParameter("method");
//        switch (method){
//            case "register":
//                doRegister(request, response);
//                break;
//            case "selectByPage":
//                doSelectByPage(request, response);
//                break;
//        }
//    }

    private String selectByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
//        request.getRequestDispatcher("userList.jsp").forward(request, response);
        return "dispatcher:userList.jsp";
    }


    private String register(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        //解决post 请求乱码的问题
//        request.setCharacterEncoding("utf-8");
//        Users u = new Users();
//        u.setUname(request.getParameter("uname")) ;
//        u.setUage(Integer.valueOf(request.getParameter("uage")));
//        u.setUbirthday(request.getParameter("ubirthday"));
//        u.setUcity(request.getParameter("ucity"));
//        u.setUpassword(request.getParameter("upassword"));
//        u.setUsex(request.getParameter("usex"));
//        String[] hobbys = request.getParameterValues("uhobby");
//        if (hobbys!=null){
//            u.setUhobby(Arrays.toString(hobbys));
//        }
        //用对象接收请求中所有数据的方法
        Users u = this.getRequestParams(Users.class,request);
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
//            request.getRequestDispatcher("login.jsp").forward(request, response);
//            重定向
//            response.sendRedirect("login.jsp");
            return "dispatcher:login.jsp";

        }else {
            response.getWriter().write("注册失败!");
            //五秒跳转meta标签
//            response.setHeader("refresh","5;url=register.html");
            //转发
//            request.getRequestDispatcher("register.html").forward(request, response);
            return "dispatcher:register.html";
            //重定向
//            response.sendRedirect("register.html");
        }
    }
    private String addusers(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Users u = this.getRequestParams(Users.class, request);
        UsersServiceImpl usersService = new UsersServiceImpl();
        int result =0;
        try {
            result = usersService.addUsers(u);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        response.setContentType("text/html;charset=utf-8");
        if (result>0){

            response.getWriter().write("注册成功");
//           没有数据   request.getRequestDispatcher("userList.jsp").forward(request,response);
//            response.sendRedirect(request.getContextPath()+"/SelectUsersByPageServlet");
            return "redirect:/SelectUsersByPageServlet";
        }else {
            response.getWriter().write("注册失败");
//            request.getRequestDispatcher("/userList.jsp").forward(request,response);
//            response.sendRedirect(request.getContextPath()+"/AddUsersServlet");
            return "dispatcher:userList.jsp";
        }

    }

}
