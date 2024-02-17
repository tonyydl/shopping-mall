package com.tonyydl.shoppingmallapp.ui.product.details

import androidx.lifecycle.viewModelScope
import com.tonyydl.shoppingmallapp.BaseViewModel
import com.tonyydl.shoppingmallapp.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : BaseViewModel<ProductDetailsUiState, ProductDetailsUiEvent>() {

    override fun setInitialState(): ProductDetailsUiState = ProductDetailsUiState()

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