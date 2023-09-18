package com.tonyydl.springbootmall.service;

import com.tonyydl.springbootmall.data.dto.UserLoginRequestDTO;
import com.tonyydl.springbootmall.data.dto.UserRegisterRequestDTO;
import com.tonyydl.springbootmall.data.po.UserPO;

public interface UserService {
    UserPO getUserById(Integer userId);

    Integer register(UserRegisterRequestDTO userRegisterRequestDTO);

    UserPO login(UserLoginRequestDTO userLoginRequestDTO);
}
