package com.tonyydl.springbootmall.controller;

import com.tonyydl.springbootmall.data.dto.UserDTO;
import com.tonyydl.springbootmall.data.dto.UserLoginRequestDTO;
import com.tonyydl.springbootmall.data.dto.UserRegisterRequestDTO;
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
    public ResponseEntity<UserDTO> register(@RequestBody @Valid UserRegisterRequestDTO userRegisterRequestDTO) {
        Integer userId = userService.register(userRegisterRequestDTO);

        UserDTO userDTO = userService.getUserById(userId).toDTO();

        return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
    }

    @PostMapping("/users/login")
    public ResponseEntity<UserDTO> login(@RequestBody @Valid UserLoginRequestDTO userLoginRequestDTO) {
        UserDTO userDTO = userService.login(userLoginRequestDTO).toDTO();

        return ResponseEntity.ok().body(userDTO);
    }
}
