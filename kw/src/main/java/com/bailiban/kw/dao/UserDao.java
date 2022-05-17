package com.bailiban.kw.dao;

import com.bailiban.kw.domain.User;

public interface UserDao {
     //根据用户名和密码查询用户（登录）
     User userLogin(String username, String password);

     //用户注册
     void userRegister(User user);

     //根据用户名查询用户
     User selectUserByUsername(String username);
}
