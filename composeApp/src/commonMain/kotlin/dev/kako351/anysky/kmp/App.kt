package dev.kako351.anysky.kmp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.kako351.anysky.kmp.data.TokenDataStore
import dev.kako351.anysky.kmp.data.model.auth.AccessToken
import dev.kako351.anysky.kmp.ui.FeedsScreen
import dev.kako351.anysky.kmp.ui.LoginScreen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview

enum class AppScreen {
    Login,
    Feeds,
}

@Composable
@Preview
fun App(
    navHostController: NavHostController = rememberNavController()
) {
    MaterialTheme {
        NavHost(
            navController = navHostController,
            startDestination = AppScreen.Login.name,
            modifier = Modifier.fillMaxSize()
        ) {
            composable(AppScreen.Login.name) {
                LoginScreen(
                    onLoginClicked = {
                        navHostController.navigate(AppScreen.Feeds.name)
                    }
                )
            }
            composable(AppScreen.Feeds.name) {
                FeedsScreen()
            }
        }
    }
}

class MainViewModel(
    private val tokenDataStore: TokenDataStore
): ViewModel() {
    val state = MutableStateFlow("Hello, World")

    init {
        viewModelScope.launch {
            tokenDataStore.accessToken?.collect {
                state.value = it.value
            }
        }
    }

    fun onClick() {
        viewModelScope.launch {
            tokenDataStore.saveAccessToken(AccessToken("test"))
        }
    }
}