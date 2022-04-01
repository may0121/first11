package com.qf.day10.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Date;
import java.util.Arrays;
import java.util.Map;

//所有servlet 继承这个父类
@WebServlet("/FatherServlet")
public class FatherServlet extends HttpServlet {
    @Override

    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        try {
            //接收请求中的标记参数
            String method = req.getParameter("method");
            System.out.println(method);
            //获取当前反射对象
            Class clazz = this.getClass();
            //请求方法名和请求参数一样
            Method methodObject = clazz.getDeclaredMethod(method,HttpServletRequest.class,HttpServletResponse.class);
            //设置访问权限
            methodObject.setAccessible(true);
            //用方法的反射对象调用方法，并接收方法返回结果

            String result = (String) methodObject.invoke(this,req,res);
            //统一处理响应
            //获得响应的内容
            String content = result.substring(result.indexOf(":")+1,result.length());
            if (result.startsWith("refresh:")){
                //几秒跳转
                res.setHeader("refresh",content);
            }else if (result.startsWith("redirect:")){
                res.sendRedirect(content);
            }else if (result.startsWith("dispatcher:")){
                req.getRequestDispatcher(content).forward(req,res);
            }else if (result.startsWith("ajax:")){
                res.getWriter().write(content);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //封装用对象接收请求中数据的方法
    //前提是对象的属性名与请求中的参数名一致
    public <T> T getRequestParams(Class<T> clazz,HttpServletRequest request) {
        //获得当前类的实例对象
        try {
            T t1 = clazz.newInstance();
            //获得请求中的所有数据
//   用数组存多选
            Map<String, String[]> map1 = request.getParameterMap();
            //遍历map中的key
            for (String key : map1.keySet()) {
                //参数名key,与对象属性名一致，通过key名得到 属性反射值
                //将属性反射对象value设置到属性中,排除请求标记
                if (!key.equals("method")) {
                    //通过key名得到属性反射对象
                    Field fieldObject = clazz.getDeclaredField(key);
                    //设置访问属性
                    fieldObject.setAccessible(true);
                    //判断属性的数据类型,
                    if (fieldObject.getGenericType().toString().contains("String")) {
                        //将value值转换为String类型
                        String value = Arrays.toString(map1.get(key));
                        fieldObject.set(t1, value.substring(1, value.length() - 1));
                    } else if (fieldObject.getGenericType().toString().contains("Integer")) {
                        //将value值转换为String类型
                        String value = Arrays.toString(map1.get(key));
                        fieldObject.set(t1, Integer.valueOf(value.substring(1, value.length() - 1)));

                    } else if (fieldObject.getGenericType().toString().contains("Double")) {
                        //将value值转换为String类型
                        String value = Arrays.toString(map1.get(key));
                        fieldObject.set(t1, Double.valueOf(value.substring(1, value.length() - 1)));

                    } else if (fieldObject.getGenericType().toString().contains("Long")) {
                        //将value值转换为String类型
                        String value = Arrays.toString(map1.get(key));
                        fieldObject.set(t1, Long.valueOf(value.substring(1, value.length() - 1)));

                    } else if (fieldObject.getGenericType().toString().contains("Float")) {
                        //将value值转换为String类型
                        String value = Arrays.toString(map1.get(key));
                        fieldObject.set(t1, Float.valueOf(value.substring(1, value.length() - 1)));

                    } else if (fieldObject.getGenericType().toString().contains("Character")) {
                        //将value值转换为String类型
                        String value = Arrays.toString(map1.get(key));
                        fieldObject.set(t1, (value.substring(1, value.length() - 1)).charAt(0));

                    } else if (fieldObject.getGenericType().toString().contains("Date")) {
                        //将value值转换为String类型
                        String value = Arrays.toString(map1.get(key));
                        fieldObject.set(t1, Date.valueOf(value.substring(1, value.length() - 1)));
                    }
                }
            }
            return t1;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}

