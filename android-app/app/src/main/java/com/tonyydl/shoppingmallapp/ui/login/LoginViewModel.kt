package com.tonyydl.shoppingmallapp.ui.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.tonyydl.shoppingmallapp.BaseViewModel
import com.tonyydl.shoppingmallapp.R
import com.tonyydl.shoppingmallapp.repository.UserRepository
import com.tonyydl.shoppingmallapp.utils.StringValue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository
) : BaseViewModel<LoginUiState, LoginUiEvent>() {
    override fun setInitialState(): LoginUiState = LoginUiState()

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
                userRepository.login(account, password)
                state =
                    state.copy(resultMessage = StringValue.StringResource(R.string.login_successful))
                sendUiEvent(LoginUiEvent.NavigateToProductPage)
            } catch (e: Exception) {
                state =
                    state.copy(resultMessage = StringValue.StringResource(R.string.login_invalid))
            }
            state = state.copy(isLoading = false)
        }
    }
}