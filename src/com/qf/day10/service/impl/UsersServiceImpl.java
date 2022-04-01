package com.qf.day10.service.impl;

import com.qf.day10.Vo.LoginVo;
import com.qf.day10.dao.UsersDAO;
import com.qf.day10.dao.impl.UsersDAOImpl;
import com.qf.day10.entity.Users;
import com.qf.day10.service.UsersService;
import com.qf.day10.tool.PageTool;

import java.sql.SQLException;
import java.util.List;

public class UsersServiceImpl implements UsersService {
    UsersDAO usersDAO = new UsersDAOImpl();
    @Override
    public int register(Users u) throws SQLException {
        return usersDAO.addUser(u);
    }

    @Override
    public boolean login(LoginVo u) throws SQLException {
        Users users = usersDAO.selectUserByUser(u);
        if (users==null){
            return false;
        }
        return true;
    }

    @Override
    public List<Users> selectUsersByPage(PageTool p) throws SQLException {
        //初始化分页工具对象
        p.setPageCount(10);
        p.setStart((p.getCurrPage() - 1) * p.getPageCount());
        //总记录数
        p.setTotalCount(usersDAO.selectAllCount());
        //总页数
        if (p.getTotalCount() % p.getPageCount() == 0) {
            p.setTotalPage(p.getTotalCount() / p.getPageCount());
        } else {
            p.setTotalPage(p.getTotalCount() / p.getPageCount() + 1);
        }
        return usersDAO.selectUserByPage(p);


    }

    @Override
    public int delUsers(String  uid) throws SQLException {


        return  usersDAO.delUsers(uid);
    }

    @Override
    public int addUsers(Users u) throws SQLException {
      return   usersDAO.addUser(u);
    }

    @Override
    public Users selectUpdateUserById(String uid) throws SQLException {
        return usersDAO.selectUpdateUserById(uid);
    }



    @Override
    public int updateUserByUid(Users user) throws SQLException {
        return usersDAO.updateUserByUid(user);
    }

    @Override
    public Users showPasswordUser(String uname) throws SQLException {
        return usersDAO.showPasswordUser(uname);
    }

    @Override
    public void setPassword(String uname, String newupassword) throws SQLException {
        usersDAO.setPassword(uname,newupassword);
    }



    @Override
    public Users selectUserByUname(String name) throws SQLException {
        return usersDAO.selectUserByUname(name);
    }

    @Override
    public int updateMess(Users user) throws SQLException {
        return usersDAO.updateMess(user);
    }


    @Override
    public Users selectPassByName(String uname) throws SQLException {
        return usersDAO.selectPassByName(uname);
    }

    @Override
    public int updatePassword(Users user) throws SQLException {
        return usersDAO.updatePassword(user);
    }



}


