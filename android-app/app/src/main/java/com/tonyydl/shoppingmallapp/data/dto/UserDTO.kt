package com.tonyydl.shoppingmallapp.data.dto

import com.google.gson.annotations.SerializedName
import java.util.Date

data class UserDTO(
    @SerializedName("user_id") val userId: String,
    val email: String,
    val password: String,
    @SerializedName("created_date") val createdDate: Date,
    @SerializedName("last_modified_date") val lastModifiedDate: Date
)