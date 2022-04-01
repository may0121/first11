package com.qf.day10.dao;

import com.qf.day10.Vo.LoginVo;
import com.qf.day10.entity.Users;
import com.qf.day10.tool.PageTool;

import java.sql.SQLException;
import java.util.List;

public interface UsersDAO {


    int delUsers(String uid) throws SQLException;
    int addUser(Users u) throws SQLException;

//查询总记录数
    Integer selectAllCount() throws SQLException;
    //分页查询记录数
    List<Users> selectUserByPage(PageTool p) throws SQLException;


    Users selectUserByUser(LoginVo u) throws SQLException;

    Users selectUpdateUserById(String uid) throws SQLException;

    int updateUserByUid(Users user) throws SQLException;




    Users showPasswordUser(String uname) throws SQLException;

    void setPassword(String uname, String newupassword) throws SQLException;




    Users selectUserByUname(String name) throws SQLException;

    int updateMess(Users user) throws SQLException;

    Users selectPassByName(String uname) throws SQLException;

    int updatePassword(Users users) throws SQLException;

}
