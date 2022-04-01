package com.qf.day10.service;

import com.qf.day10.Vo.LoginVo;
import com.qf.day10.entity.Users;
import com.qf.day10.tool.PageTool;

import java.sql.SQLException;
import java.util.List;

public interface UsersService {
    int register(Users u) throws SQLException;

    boolean login(LoginVo u) throws SQLException;

    List<Users> selectUsersByPage(PageTool p) throws SQLException;


    int delUsers(String uid) throws SQLException;


    int addUsers(Users u) throws SQLException;

//根据用户编号查询要修改的用户信息
    Users selectUpdateUserById(String uid) throws SQLException;

    int updateUserByUid(Users user) throws SQLException;




    Users showPasswordUser(String uname) throws SQLException;

    void setPassword(String uname, String newupassword) throws SQLException;



    Users selectUserByUname(String name) throws SQLException;

    int updateMess(Users user) throws SQLException;


    Users selectPassByName(String uname) throws SQLException;

    int updatePassword(Users user) throws SQLException;

}
