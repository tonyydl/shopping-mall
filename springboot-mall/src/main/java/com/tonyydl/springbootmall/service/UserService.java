package com.tonyydl.springbootmall.service;

import com.tonyydl.springbootmall.dto.UserRegisterRequest;
import com.tonyydl.springbootmall.model.User;

public interface UserService {
    User getUserById(Integer userId);

    Integer register(UserRegisterRequest userRegisterRequest);
}
