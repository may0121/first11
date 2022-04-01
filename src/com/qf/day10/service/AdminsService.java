package com.qf.day10.service;

import com.qf.day10.Vo.LoginVo;

import java.sql.SQLException;

public interface AdminsService {
    boolean login(LoginVo u) throws SQLException;
}
