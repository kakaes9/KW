package com.bailiban.kw.service.impl;

import com.bailiban.kw.dao.UserDao;
import com.bailiban.kw.dao.impl.UserDaoImpl;
import com.bailiban.kw.domain.User;
import com.bailiban.kw.service.UserService;

public class UserServiceImpl implements UserService {
    //创建dao
    private UserDao userDao =new UserDaoImpl();
    @Override
    public User userLogin(String username, String password) {
        return userDao.userLogin(username,password);
    }

    @Override
    public void userRegister(User user) {
        userDao.userRegister(user);
    }

    @Override
    public User selectUserByUsername(String username) {
        return userDao.selectUserByUsername(username);

    }


}
