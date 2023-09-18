package com.tonyydl.springbootmall.service.impl;

import com.tonyydl.springbootmall.data.dto.UserLoginRequestDTO;
import com.tonyydl.springbootmall.data.dto.UserRegisterRequestDTO;
import com.tonyydl.springbootmall.data.po.UserPO;
import com.tonyydl.springbootmall.repository.UserRepository;
import com.tonyydl.springbootmall.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.server.ResponseStatusException;

@Component
public class UserServiceImpl implements UserService {

    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserPO getUserById(Integer userId) {
        return userRepository.findById(userId).orElseThrow(() -> {
            log.warn("userId {} 不存在", userId);
            return new ResponseStatusException(HttpStatus.BAD_REQUEST);
        });
    }

    @Override
    public Integer register(UserRegisterRequestDTO userRegisterRequestDTO) {
        // 檢查註冊的 email
        UserPO userPO = userRepository.findByEmail(userRegisterRequestDTO.getEmail());

        if (userPO != null) {
            log.warn("email {} has been registered!", userRegisterRequestDTO.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        // 使用 MD5 生成密碼的雜湊值
        String hashedPassword = DigestUtils.md5DigestAsHex(userRegisterRequestDTO.getPassword().getBytes());
        userRegisterRequestDTO.setPassword(hashedPassword);

        // 創建帳號
        return userRepository.save(userRegisterRequestDTO.toPo()).getUserId();
    }

    @Override
    public UserPO login(UserLoginRequestDTO userLoginRequestDTO) {
        UserPO userPO = userRepository.findByEmail(userLoginRequestDTO.getEmail());

        // 檢查 user 是否存在
        if (userPO == null) {
            log.warn("email {} hasn't been registered yet!", userLoginRequestDTO.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        // 使用 MD5 生成密碼的雜湊值
        String hashedPassword = DigestUtils.md5DigestAsHex(userLoginRequestDTO.getPassword().getBytes());

        // 比較密碼
        if (userPO.getPassword().equals(hashedPassword)) {
            return userPO;
        } else {
            log.warn("email {} password are incorrect!", userLoginRequestDTO.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
