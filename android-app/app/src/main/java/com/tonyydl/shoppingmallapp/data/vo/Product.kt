package com.tonyydl.shoppingmallapp.data.vo

import com.tonyydl.shoppingmallapp.data.ProductCategory

data class Product(
    val productId: Int,
    val productName: String,
    val category: ProductCategory,
    val imageUrl: String,
    val price: Int,
    val stock: Int,
    val description: String,
    val createdDate: Long,
    val lastModifiedDate: Long
)