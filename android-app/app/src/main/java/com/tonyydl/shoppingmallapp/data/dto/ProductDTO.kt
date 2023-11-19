package com.tonyydl.shoppingmallapp.data.dto

import com.google.gson.annotations.SerializedName
import java.util.Date

data class ProductDTO(
    @SerializedName("product_id") val productId: Int,
    @SerializedName("product_name") val productName: String,
    val category: ProductCategory,
    @SerializedName("image_url") val imageUrl: String,
    val price: Int,
    val stock: Int,
    val description: String,
    @SerializedName("created_date") val createdDate: Date,
    @SerializedName("last_modified_date") val lastModifiedDate: Date
)