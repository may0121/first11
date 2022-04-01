package com.qf.day10.tool;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import java.util.Properties;

public class DruidTool {
    /**
     * 声明druid连接池
     */
    public static DruidDataSource pool;
    /**
     * 创建配置文件对象
     */
    public static Properties p=new Properties();
    /**
     * 用静态代码块加载配置文件,初始化连接池
     */
    static{
        try {
            //加载配置文件
            p.load(DruidTool.class.getClassLoader().getResourceAsStream("jdbc.properties"));
            //初始化连接池,自动识别配置信息,要求配置文件中key要与druid底层属性名一致
            pool= (DruidDataSource) DruidDataSourceFactory.createDataSource(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
