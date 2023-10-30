package com.tonyydl.shoppingmallapp.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tonyydl.shoppingmallapp.R
import com.tonyydl.shoppingmallapp.utils.StringValue
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    private val _toastMessage = MutableSharedFlow<StringValue>()
    val toastMessage = _toastMessage.asSharedFlow()

    var account by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    fun updateAccount(account: String) {
        this.account = account
    }

    fun updatePassword(password: String) {
        this.password = password
    }

    fun performLogin() {
        if (account.isBlank() || password.isBlank()) {
            showToast(StringValue.StringResource(R.string.login_blank_invalid))
            return
        }
        viewModelScope.launch {
            updateLoadingState(true)
            delay(1_000) // TODO: request API and wait for response
            updateResultState(StringValue.StringResource(R.string.login_invalid)) // TODO: temporary show fail result for user
            updateLoadingState(false)
        }
    }

    private fun showToast(stringValue: StringValue) {
        viewModelScope.launch {
            _toastMessage.emit(stringValue)
        }
    }

    private fun updateLoadingState(isLoading: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(isLoading = isLoading)
        }
    }

    private fun updateResultState(stringValue: StringValue) {
        _uiState.update { currentState ->
            currentState.copy(resultMessage = stringValue)
        }
    }
}