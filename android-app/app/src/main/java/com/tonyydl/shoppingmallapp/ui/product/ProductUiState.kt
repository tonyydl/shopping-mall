package com.tonyydl.shoppingmallapp.ui.product

import com.tonyydl.shoppingmallapp.data.dto.ProductDTO

data class ProductUiState(
    val isLoading: Boolean = false,
    val productList: List<ProductDTO> = emptyList()
)