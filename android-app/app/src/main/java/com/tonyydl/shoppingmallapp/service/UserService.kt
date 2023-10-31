package com.tonyydl.shoppingmallapp.service

import com.tonyydl.shoppingmallapp.data.dto.UserDTO
import com.tonyydl.shoppingmallapp.data.dto.UserLoginRequestDTO
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {

    @POST("/users/login")
    suspend fun login(@Body userLoginRequestDTO: UserLoginRequestDTO): UserDTO
}