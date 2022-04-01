package com.qf.day10.service.impl;

import com.qf.day10.Vo.LoginVo;
import com.qf.day10.dao.AdminsDAO;
import com.qf.day10.dao.impl.AdminsDAOImpl;
import com.qf.day10.entity.Admins;
import com.qf.day10.service.AdminsService;

import java.sql.SQLException;

public class AdminsServiceImpl implements AdminsService {
    AdminsDAO adminsDAO = new AdminsDAOImpl();


    @Override
    public boolean login(LoginVo u) throws SQLException {
      Admins a = adminsDAO.selectAdminsByNameAndPassword(u);

        if (a!=null){
            return true;
        }
        return false;
    }
}
