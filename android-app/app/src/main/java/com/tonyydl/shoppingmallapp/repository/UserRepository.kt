package com.tonyydl.shoppingmallapp.repository

import com.tonyydl.shoppingmallapp.data.dto.UserDTO
import com.tonyydl.shoppingmallapp.data.dto.UserLoginRequestDTO
import com.tonyydl.shoppingmallapp.service.UserService

class UserRepository(private val userService: UserService) {
    suspend fun login(userLoginRequestDTO: UserLoginRequestDTO): UserDTO = userService.login(userLoginRequestDTO)
}