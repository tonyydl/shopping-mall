package com.tonyydl.shoppingmallapp.repository

import com.tonyydl.shoppingmallapp.data.dto.UserDTO
import com.tonyydl.shoppingmallapp.data.dto.UserLoginRequestDTO
import com.tonyydl.shoppingmallapp.service.UserService
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userService: UserService
) {
    suspend fun login(
        email: String,
        password: String
    ): UserDTO = userService.login(UserLoginRequestDTO(email, password))
}