package com.tonyydl.shoppingmallapp.data.dto

import com.google.gson.annotations.SerializedName
import com.tonyydl.shoppingmallapp.data.Mapper
import com.tonyydl.shoppingmallapp.data.vo.User

data class UserDTO(
    @SerializedName("user_id") val userId: Int = 0,
    val email: String? = null,
    @SerializedName("created_date") val createdDate: Long? = null,
    @SerializedName("last_modified_date") val lastModifiedDate: Long? = null
) : Mapper<User> {
    override fun transform(): User {
        return User(
            userId = userId,
            email = email.orEmpty(),
            createdDate = createdDate ?: 0L,
            lastModifiedDate = lastModifiedDate ?: 0L
        )
    }
}