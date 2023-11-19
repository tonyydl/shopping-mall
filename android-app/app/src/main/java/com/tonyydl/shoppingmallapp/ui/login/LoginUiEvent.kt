package com.tonyydl.shoppingmallapp.ui.login

sealed interface LoginUiEvent {
    object NavigateToProductPage : LoginUiEvent
    object LoginInvalid : LoginUiEvent
}