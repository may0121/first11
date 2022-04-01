package com.qf.day10.dao;

import com.qf.day10.Vo.LoginVo;
import com.qf.day10.entity.Admins;

import java.sql.SQLException;

public interface AdminsDAO {
    Admins selectAdminsByNameAndPassword(LoginVo u) throws SQLException;

}
