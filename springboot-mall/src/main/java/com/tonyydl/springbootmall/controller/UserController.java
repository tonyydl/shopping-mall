package com.tonyydl.springbootmall.controller;

import com.tonyydl.springbootmall.data.dto.UserLoginRequestDTO;
import com.tonyydl.springbootmall.data.dto.UserRegisterRequestDTO;
import com.tonyydl.springbootmall.data.po.UserPO;
import com.tonyydl.springbootmall.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users/register")
    public ResponseEntity<UserPO> register(@RequestBody @Valid UserRegisterRequestDTO userRegisterRequestDTO) {
        Integer userId = userService.register(userRegisterRequestDTO);

        UserPO userPO = userService.getUserById(userId);

        return ResponseEntity.status(HttpStatus.CREATED).body(userPO);
    }

    @PostMapping("/users/login")
    public ResponseEntity<UserPO> login(@RequestBody @Valid UserLoginRequestDTO userLoginRequestDTO) {
        UserPO userPO = userService.login(userLoginRequestDTO);

        return ResponseEntity.ok().body(userPO);
    }
}
