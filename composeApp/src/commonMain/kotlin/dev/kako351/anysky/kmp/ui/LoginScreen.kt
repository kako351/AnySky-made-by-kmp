package dev.kako351.anysky.kmp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LoginScreen(
    onLogin: () -> Unit,
    viewModel: LoginViewModel = koinViewModel()
) {
    val state = viewModel.state.collectAsState()
    val uiState = viewModel.uiState.collectAsState()

    val error: State<Boolean> = remember(state.value) {
        derivedStateOf {
            state.value is LoginViewModel.LoginState.LoginFailed
        }
    }

    LaunchedEffect(state.value) {
        when(state.value) {
            is LoginViewModel.LoginState.LoginSuccess,
            LoginViewModel.LoginState.AlreadyLogin -> {
                onLogin()
            }
            else -> {}
        }
    }
    MaterialTheme {
        Column(
            Modifier.fillMaxWidth().padding(top = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text("Login Screen")
            TextField(
                value = uiState.value.email,
                onValueChange = {
                    viewModel.updateEmail(it)
                },
                label = {
                    Text("Email")
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )
            TextField(
                value = uiState.value.password,
                onValueChange = {
                    viewModel.updatePassword(it)
                },
                label = {
                    Text("Password")
                },
                visualTransformation = PasswordVisualTransformation()
            )
            Button(
                onClick = {
                    viewModel.onLoginClicked()
                }
            ) {
                Text("Login")
            }

            if(error.value) {
                Text("Error", color = MaterialTheme.colors.error)
            }
        }
    }
}
