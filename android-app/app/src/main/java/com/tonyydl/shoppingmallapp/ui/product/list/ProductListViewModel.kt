package com.tonyydl.shoppingmallapp.ui.product.list

import androidx.lifecycle.viewModelScope
import com.tonyydl.shoppingmallapp.BaseViewModel
import com.tonyydl.shoppingmallapp.repository.ProductRepository
import com.tonyydl.shoppingmallapp.service.RetrofitManager
import kotlinx.coroutines.launch

class ProductListViewModel : BaseViewModel<ProductListUiState, ProductListUiEvent>() {
    override fun setInitialState(): ProductListUiState = ProductListUiState()

    private val productRepository by lazy { ProductRepository(RetrofitManager.productService) }

    init {
        getProducts()
    }

    fun getProducts() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            try {
                val page = productRepository.getProducts()
                state = state.copy(
                    productList = page.results.map { it.transform() }
                )
            } catch (e: Exception) {
                // TODO: handle exception
            }
            state = state.copy(isLoading = false)
        }
    }
}