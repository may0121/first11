package com.qf.day10.servlet;

import com.qf.day10.Vo.LoginVo;
import com.qf.day10.entity.Users;
import com.qf.day10.service.AdminsService;
import com.qf.day10.service.UsersService;
import com.qf.day10.service.impl.AdminsServiceImpl;
import com.qf.day10.service.impl.UsersServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

@WebServlet("/CommonServlet")
public class CommonServlet extends FatherServlet {

//普通用户和管理员修改密码
    public String updatePassword (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Users user = this.getRequestParams(Users.class,request);
        UsersService usersService = new UsersServiceImpl();
        int result=0;
        try {
          result = usersService.updatePassword(user);
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        if (result>0){
            return "dispatcher:login.jsp";
        }

       return "dispatcher:register.html";
    }

    public String selectPassByName (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    String uname = request.getParameter("uname");
        UsersService usersService = new UsersServiceImpl();
        Users users=null;
        try {
            users = usersService.selectPassByName(uname);
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        request.setAttribute("loginUser",users);
       return "dispatcher:updatePassword.jsp";

    }

    private String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        /**
         * 1.接收数据
         * 2.调用业务层得到结果
         *3.相应
         */
        LoginVo u = new LoginVo();

        u.setUname(request.getParameter("uname"));
        u.setUpassword(request.getParameter("upassword"));
        u.setUrole(request.getParameter("urole"));
        System.out.println("登录用户信息：" + u.toString() );
//     接收记住我 如果多选框没有value 属性，选中得到的值是on 没有选中的值是null

        String remberMe = request.getParameter("remberMe");
        System.out.println("记住我"+remberMe);


        //接收验证码
        String cdCode = request.getParameter("cdCode");
        //获得session存的生成的验证码
        String  checkCode = (String) request.getSession().getAttribute("checkCode");
        if (checkCode.equals(cdCode)){
            //声明变量存结果
            boolean result = false;

            //        调用业务层处理请求并得到结果
            if ("student".equals(u.getUrole())){//普通用户

//       创建业务对象
                UsersService usersService = new UsersServiceImpl();
                try {
                    result = usersService.login(u);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }else {//管理员
                AdminsService adminsService = new AdminsServiceImpl();

                try {
                    result = adminsService.login(u);
                } catch (Exception throwables) {
                    throwables.printStackTrace();
                }

            }


//        响应
            if (result){
                //实现记住我功能
                if ("on".equals(remberMe)){
                    //创建cookie 对象村用户名和密码
                    Cookie cookie1 = new Cookie("uname1", u.getUname());
                    Cookie cookie2 = new Cookie("upassword1", u.getUpassword());
                    //设置cookie 的有效期
                    cookie1.setMaxAge(60);
                    cookie2.setMaxAge(60);
                    //用响应对象调用方法，将cookie 通过浏览器 存在客户端
                    response.addCookie(cookie1);
                    response.addCookie(cookie2);

                }
                //将数据存在session 对象中
                request.getSession().setAttribute("loginUser",u);
//            用转发的方式将数据带到下一个页面
//                request.getRequestDispatcher("index.jsp").forward(request, response);
                return "dispatcher:index.jsp";

            }else {
                request.setAttribute("mess","用户名或密码错误登录失败");
//                request.getRequestDispatcher("login.jsp").forward(request, response);
                 return "dispatcher:login.jsp";
            }



        }else {
            request.setAttribute("mess","验证码有误，登录失败");
//            request.getRequestDispatcher("login.jsp").forward(request, response);
            return "dispatcher:login.jsp";

        }
    }
}
