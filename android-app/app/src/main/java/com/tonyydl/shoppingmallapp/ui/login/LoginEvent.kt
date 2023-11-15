package com.tonyydl.shoppingmallapp.ui.login

sealed interface LoginEvent {
    object LoginInvalid : LoginEvent
}