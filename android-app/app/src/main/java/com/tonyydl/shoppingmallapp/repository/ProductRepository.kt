package com.tonyydl.shoppingmallapp.repository

import com.tonyydl.shoppingmallapp.data.ProductCategory
import com.tonyydl.shoppingmallapp.service.ProductService
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val productService: ProductService
) {
    suspend fun getProducts(
        category: ProductCategory? = null,
        search: String? = null,
        page: Int? = null,
        size: Int? = null,
        sort: String? = null
    ) = productService.getProducts(category, search, page, size, sort)

    suspend fun getProductById(
        productId: Int? = null
    ) = productService.getProductById(productId)
}