package com.qf.day10.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSessionBindingEvent;

@WebListener()
public class CountPeopleListener implements HttpSessionListener{

    @Override
    public void sessionCreated(HttpSessionEvent e) {
        System.out.println("来了一个客户");
        //获取当前网站人数
        Object count = e.getSession().getServletContext().getAttribute("count");
        int num = 0;
        if (count==null){//没有键值对，是第一个访问网站的人
            num =1;


        }else {
            num =Integer.valueOf(count.toString())+1;
        }
        //将当前人数存在上下文当中
        e.getSession().getServletContext().setAttribute("count",num);
        System.out.println("当前网站访问的总人数人为："+num);

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent e) {
        System.out.println("走了一个客户");

        //获得当前网站人数
        int num = (int)e.getSession().getServletContext().getAttribute("count")-1;
        e.getSession().getServletContext().setAttribute("count",num);

    }

}
