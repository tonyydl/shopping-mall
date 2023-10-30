package com.tonyydl.shoppingmallapp.ui

import com.tonyydl.shoppingmallapp.utils.StringValue

data class LoginUiState(
    val isLoading: Boolean = false,
    val resultMessage: StringValue = StringValue.Empty
)