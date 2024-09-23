package dev.kako351.anysky.kmp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import dev.kako351.anysky.kmp.data.TokenDataStore

class LoginViewModel(
    private val tokenDataStore: TokenDataStore
): ViewModel() {
    private val _state = MutableStateFlow<LoginState>(LoginState.Initial)
    val state = _state.asStateFlow()
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            tokenDataStore.accessToken?.collect {
                if(it.value.isNotEmpty()) {
                    println("already login: accessToken: ${it.value}")
                    _state.update {
                        LoginState.AlreadyLogin
                    }
                }
            }
        }
    }

    fun updateEmail(email: String) {
        _uiState.update {
            it.copy(email = email)
        }
    }

    fun updatePassword(password: String) {
        _uiState.update {
            it.copy(password = password)
        }
    }

    sealed class LoginState {
        data object Initial: LoginState()
        data object AlreadyLogin: LoginState()
        data object LoginSuccess: LoginState()
        data object LoginFailed: LoginState()
    }

    data class LoginUiState(
        val email: String = "",
        val password: String = ""
    )
}