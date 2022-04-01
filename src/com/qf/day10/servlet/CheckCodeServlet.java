package com.qf.day10.servlet;

import cn.dsna.util.images.ValidateCode;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/CheckCodeServlet")
public class CheckCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    //声明变量存验证码，高，字符个数，干扰线条数
        int width =120;
        int heigt = 40;
        int count = 4;
        int line = 10;

        //通过验证包创建验证码对象
        ValidateCode code = new ValidateCode(width,heigt,count,line);
        //获得随机生成的验证码字符
        String codeString  = code.getCode();
        System.out.println("生成的验证码为："+codeString);
        //将生成的验证码存在session对象中
        request.getSession().setAttribute("checkCode",codeString);
        //将验证码图片响应给客户端
        code.write(response.getOutputStream());

    }
}
