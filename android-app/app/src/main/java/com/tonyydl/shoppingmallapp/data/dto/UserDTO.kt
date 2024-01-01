package com.tonyydl.shoppingmallapp.data.dto

import com.google.gson.annotations.SerializedName

data class UserDTO(
    @SerializedName("user_id") val userId: String? = null,
    val email: String? = null,
    val password: String? = null,
    @SerializedName("created_date") val createdDate: Long? = null,
    @SerializedName("last_modified_date") val lastModifiedDate: Long? = null
)