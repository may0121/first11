package com.qf.day10.dao.impl;

import com.qf.day10.Vo.LoginVo;
import com.qf.day10.dao.AdminsDAO;
import com.qf.day10.entity.Admins;
import com.qf.day10.entity.Users;
import com.qf.day10.tool.DruidTool;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class AdminsDAOImpl implements AdminsDAO {
    QueryRunner qr = new QueryRunner(DruidTool.pool);


    @Override
    public Admins selectAdminsByNameAndPassword(LoginVo u) throws SQLException {
        String sql="select name,password from t_admins where name=? and password=?";
        //用dbutils工具类的执行对象调用相应方法接收结果
        Admins result=qr.query(sql,new BeanHandler<Admins>(Admins.class),u.getUname(),u.getUpassword());
        return result;
    }
}
