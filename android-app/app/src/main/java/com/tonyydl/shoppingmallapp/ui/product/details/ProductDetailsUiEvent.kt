package com.tonyydl.shoppingmallapp.ui.product.details

sealed interface ProductDetailsUiEvent {
    object GetProductFailed : ProductDetailsUiEvent
}