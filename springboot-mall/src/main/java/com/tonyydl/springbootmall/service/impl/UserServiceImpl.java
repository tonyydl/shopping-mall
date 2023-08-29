package com.tonyydl.springbootmall.service.impl;

import com.tonyydl.springbootmall.dao.UserDao;
import com.tonyydl.springbootmall.dto.UserRegisterRequest;
import com.tonyydl.springbootmall.model.User;
import com.tonyydl.springbootmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        return userDao.createUser(userRegisterRequest);
    }
}
