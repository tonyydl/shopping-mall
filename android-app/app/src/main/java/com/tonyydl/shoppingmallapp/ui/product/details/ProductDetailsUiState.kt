package com.tonyydl.shoppingmallapp.ui.product.details

import com.tonyydl.shoppingmallapp.data.vo.Product

data class ProductDetailsUiState(
    val isLoading: Boolean = false,
    val product: Product? = null
)