package com.tonyydl.shoppingmallapp.ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.tonyydl.shoppingmallapp.R
import com.tonyydl.shoppingmallapp.ui.theme.ShoppingMallTheme
import com.tonyydl.shoppingmallapp.utils.StringValue

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    showToast: (StringValue) -> Unit = { _ -> },
    loginViewModel: LoginViewModel = viewModel()
) {
    val context = LocalContext.current
    val uiState by loginViewModel.uiState.collectAsStateWithLifecycle()
    val mediumPadding = dimensionResource(R.dimen.padding_medium)
    val resultMessage = uiState.resultMessage.asString(context)

    LaunchedEffect(Unit) {
        loginViewModel.event.collect { event ->
            when (event) {
                LoginEvent.LoginInvalid -> {
                    showToast(StringValue.StringResource(R.string.login_blank_invalid))
                }
            }
        }
    }

    Column(
        modifier = modifier.padding(mediumPadding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.app_name),
            style = typography.titleLarge
        )
        LoginLayout(
            account = loginViewModel.account,
            password = loginViewModel.password,
            onAccountChanged = { loginViewModel.updateAccount(it) },
            onPasswordChanged = { loginViewModel.updatePassword(it) },
            isAccountOrPasswordWrong = false,
            onKeyboardDone = { loginViewModel.performLogin() }
        )
        if (uiState.isLoading) {
            CircularProgressIndicator()
        }
        if (!uiState.isLoading && resultMessage.isNotBlank()) {
            Text(
                text = resultMessage,
                style = typography.bodyLarge
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(mediumPadding),
            verticalArrangement = Arrangement.spacedBy(mediumPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { loginViewModel.performLogin() }
            ) {
                Text(
                    text = stringResource(R.string.button_login),
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
private fun LoginLayout(
    account: String,
    password: String,
    modifier: Modifier = Modifier,
    onAccountChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    isAccountOrPasswordWrong: Boolean = false,
    onKeyboardDone: () -> Unit
) {
    val mediumPadding = dimensionResource(R.dimen.padding_medium)
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(mediumPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(mediumPadding)
        ) {
            Text(
                text = stringResource(R.string.login_title),
                style = typography.displayMedium
            )
            OutlinedTextField(
                value = account,
                singleLine = true,
                shape = MaterialTheme.shapes.large,
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    disabledContainerColor = MaterialTheme.colorScheme.surface,
                ),
                onValueChange = onAccountChanged,
                label = {
                    Text(stringResource(R.string.enter_account))
                },
                isError = isAccountOrPasswordWrong,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next
                )
            )
            OutlinedTextField(
                value = password,
                singleLine = true,
                shape = MaterialTheme.shapes.large,
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    disabledContainerColor = MaterialTheme.colorScheme.surface,
                ),
                onValueChange = onPasswordChanged,
                label = {
                    Text(stringResource(R.string.enter_password))
                },
                isError = isAccountOrPasswordWrong,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { onKeyboardDone() }
                ),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image = if (passwordVisible)
                        Icons.Filled.Visibility
                    else Icons.Filled.VisibilityOff

                    val description = if (passwordVisible) "Hide password" else "Show password"

                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = image, description)
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    ShoppingMallTheme {
        LoginScreen()
    }
}