package com.tonyydl.shoppingmallapp.ui.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tonyydl.shoppingmallapp.R
import com.tonyydl.shoppingmallapp.data.dto.UserLoginRequestDTO
import com.tonyydl.shoppingmallapp.repository.UserRepository
import com.tonyydl.shoppingmallapp.service.RetrofitManager
import com.tonyydl.shoppingmallapp.utils.StringValue
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.lang.Exception

class LoginViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    private var state: LoginUiState
        get() = _uiState.value
        set(newState) {
            _uiState.update { newState }
        }

    private val _toastMessage = MutableSharedFlow<StringValue>()
    val toastMessage = _toastMessage.asSharedFlow()

    private val userRepository by lazy { UserRepository(RetrofitManager.userService) }

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
            state = state.copy(isLoading = true)
            try {
                val userDTO = userRepository.login(UserLoginRequestDTO(account, password))
                state = state.copy(resultMessage = StringValue.StringResource(R.string.login_successful))
                // TODO: navigate to next page
            } catch (e: Exception) {
                state = state.copy(resultMessage = StringValue.StringResource(R.string.login_invalid))
            }
            state = state.copy(isLoading = false)
        }
    }

    private fun showToast(stringValue: StringValue) {
        viewModelScope.launch {
            _toastMessage.emit(stringValue)
        }
    }
}