package com.qf.day10.dao.impl;

import com.qf.day10.Vo.LoginVo;

import com.qf.day10.dao.UsersDAO;
import com.qf.day10.entity.Users;
import com.qf.day10.tool.DruidTool;
import com.qf.day10.tool.PageTool;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;
public class UsersDAOImpl implements UsersDAO {
    /**
     * 创建dbutils工具类的执行对象
     */
    QueryRunner qr=new QueryRunner(DruidTool.pool);


    /**
     * 添加用户
     * @param u
     * @return int
     */
    @Override
    public int addUser(Users u) throws SQLException {
        //准备sql语句
        String sql="insert into t_users(uname,upassword,uage,usex,uhobby,ucity,ubirthday) values(?,?,?,?,?,?,?)";
        //用dbutils工具类的执行对象调用相应方法接收结果
        int result=qr.update(sql,u.getUname(),u.getUpassword(),u.getUage(),u.getUsex(),u.getUhobby(),u.getUcity(),u.getUbirthday());
        return result;
    }

    /**
     * 查询总记录数
     * @return Integer
     */
    @Override
    public Integer selectAllCount() throws SQLException {
        //准备sql语句
        String sql="select count(uid) from t_users";
        //用dbutils工具类的执行对象调用相应方法接收结果
        long result=qr.query(sql,new ScalarHandler<Long>());
        return (int)result;
    }

    /**
     * 分页查询当前页用户信息
     * @param p
     * @return List<Users>
     */
    @Override
    public List<Users> selectUserByPage(PageTool p) throws SQLException {
        //准备sql语句
        String sql="select uid,uname,upassword,uage,usex,uhobby,ucity,ubirthday from t_users limit ?,?";
        //用dbutils工具类的执行对象调用相应方法接收结果
        List<Users> result=qr.query(sql,new BeanListHandler<Users>(Users.class),p.getStart(),p.getPageCount());
        return result;
    }

    @Override
    public Users selectUserByUser(LoginVo u) throws SQLException {
        //准备sql语句
        String sql="select uname,upassword,uage,usex,uhobby,ucity,ubirthday from t_users where uname=? and upassword=?";
        //用dbutils工具类的执行对象调用相应方法接收结果
        Users result=qr.query(sql,new BeanHandler<Users>(Users.class),u.getUname(),u.getUpassword());
        return result;
    }
//根据uid查询修改信息
    @Override
    public Users selectUpdateUserById(String uid) throws SQLException {
        String sql="select uid,uname,upassword,uage,usex,uhobby,ucity,ubirthday from t_users where uid=?";
        //用dbutils工具类的执行对象调用相应方法接收结果  new BeanHandler<Users>(Users.class)对象转换器
        Users result =qr.query(sql,new BeanHandler<Users>(Users.class),uid);
        return result;
    }

    @Override
    public int updateUserByUid(Users user) throws SQLException {
       String sql = "update t_users set uname=?,uage=?,usex=?,uhobby=?,ucity=?,ubirthday=? where uid =?  ";
       int result = qr.update(sql,user.getUname(),user.getUage(),user.getUsex(),user.getUhobby(),user.getUcity(),user.getUbirthday(),user.getUid());
       return result;
    }

    @Override
    public Users showPasswordUser(String uname) throws SQLException {
        String sql = "select * from t_users where uname=?";
        Users result = qr.query(sql, new BeanHandler<>(Users.class), uname);
        return result;
    }

    @Override
    public void setPassword(String uname,String newPassword) throws SQLException {
        String sql = "update t_users set upassword=? where uname=?";
        qr.update(sql,newPassword,uname);
    }


    @Override
    public Users selectUserByUname(String name) throws SQLException {
        String sql = "select * from t_users where uname=?";
        Users result =qr.query(sql,new BeanHandler<>(Users.class), name);
        return result;
    }


    @Override
    public int updateMess(Users user) throws SQLException {
        String sql = "update t_users set uage=?,usex=?,uhobby=?,ucity=?,ubirthday=? where uname=?  ";
        int result = qr.update(sql,user.getUage(),user.getUsex(),user.getUhobby(),user.getUcity(),user.getUbirthday(),user.getUname());
        return result;
    }

    @Override
    public Users selectPassByName(String uname) throws SQLException {
       String sql ="select * from t_users where uname=?";
       Users result =qr.query(sql,new BeanHandler<>(Users.class),uname);
       return result;
    }

    @Override
    public int updatePassword(Users user) throws SQLException {
      String sql = "update t_users set upassword=? where uname=?";
      int result = qr.update(sql,user.getUpassword(),user.getUname());
      return result;
    }


    @Override
    public int delUsers(String uid) throws SQLException {
        String sql = "delete from t_users where uid=?";

        return  qr.update(sql,uid);
    }



}



