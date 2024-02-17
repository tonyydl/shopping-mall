package com.tonyydl.shoppingmallapp.ui.product.list

import androidx.lifecycle.viewModelScope
import com.tonyydl.shoppingmallapp.BaseViewModel
import com.tonyydl.shoppingmallapp.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val productRepository: ProductRepository
): BaseViewModel<ProductListUiState, ProductListUiEvent>() {
    override fun setInitialState(): ProductListUiState = ProductListUiState()

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