package com.tonyydl.shoppingmallapp.ui.product.list

import com.tonyydl.shoppingmallapp.data.vo.Product

data class ProductListUiState(
    val isLoading: Boolean = false,
    val productList: List<Product> = emptyList()
)