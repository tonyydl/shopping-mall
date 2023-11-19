package com.tonyydl.shoppingmallapp.ui.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.tonyydl.shoppingmallapp.BaseViewModel
import com.tonyydl.shoppingmallapp.R
import com.tonyydl.shoppingmallapp.data.dto.UserLoginRequestDTO
import com.tonyydl.shoppingmallapp.repository.UserRepository
import com.tonyydl.shoppingmallapp.service.RetrofitManager
import com.tonyydl.shoppingmallapp.utils.StringValue
import kotlinx.coroutines.launch
import java.lang.Exception

class LoginViewModel : BaseViewModel<LoginUiState, LoginUiEvent>() {
    override fun setInitialState(): LoginUiState = LoginUiState()

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
            sendUiEvent(LoginUiEvent.LoginInvalid)
            return
        }
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            try {
                val userDTO = userRepository.login(UserLoginRequestDTO(account, password))
                state = state.copy(resultMessage = StringValue.StringResource(R.string.login_successful))
                sendUiEvent(LoginUiEvent.NavigateToProductPage)
            } catch (e: Exception) {
                state = state.copy(resultMessage = StringValue.StringResource(R.string.login_invalid))
            }
            state = state.copy(isLoading = false)
        }
    }
}