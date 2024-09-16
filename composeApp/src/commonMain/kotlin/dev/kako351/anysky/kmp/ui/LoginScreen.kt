package dev.kako351.anysky.kmp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LoginScreen(
    onLoginClicked: () -> Unit,
    viewModel: LoginViewModel = koinViewModel()
) {
    val state = viewModel.state.collectAsState()
    LaunchedEffect(state.value) {
        if(state.value is LoginViewModel.LoginState.AlreadyLogin) {
            onLoginClicked()
        }
    }
    MaterialTheme {
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Login Screen")
            Button(
                onClick = {
                    onLoginClicked()
                }
            ) {
                Text("Login")
            }
        }
    }
}
