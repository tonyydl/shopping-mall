package com.tonyydl.springbootmall.dao;

import com.tonyydl.springbootmall.dto.UserRegisterRequest;
import com.tonyydl.springbootmall.model.User;

public interface UserDao {

    User getUserById(Integer userId);

    User getUserByEmail(String email);

    Integer createUser(UserRegisterRequest userRegisterRequest);
}
