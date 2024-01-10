package com.tonyydl.shoppingmallapp.ui.product.details

import androidx.lifecycle.viewModelScope
import com.tonyydl.shoppingmallapp.BaseViewModel
import com.tonyydl.shoppingmallapp.repository.ProductRepository
import com.tonyydl.shoppingmallapp.service.RetrofitManager
import kotlinx.coroutines.launch

class ProductDetailsViewModel : BaseViewModel<ProductDetailsUiState, ProductDetailsUiEvent>() {

    override fun setInitialState(): ProductDetailsUiState = ProductDetailsUiState()

    private val productRepository by lazy { ProductRepository(RetrofitManager.productService) }

    fun getProductById(productId: Int) {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            try {
                val productDTO = productRepository.getProductById(productId)
                state = state.copy(product = productDTO.transform())
            } catch (e: Exception) {
                sendUiEvent(ProductDetailsUiEvent.GetProductFailed)
            }
            state = state.copy(isLoading = false)
        }
    }
}