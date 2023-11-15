package com.tonyydl.shoppingmallapp.ui.login

sealed interface LoginUiEvent {
    object LoginInvalid : LoginUiEvent
}