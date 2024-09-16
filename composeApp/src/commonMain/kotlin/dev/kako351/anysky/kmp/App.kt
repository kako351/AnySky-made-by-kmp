package dev.kako351.anysky.kmp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import anysky_made_by_kmp.composeapp.generated.resources.Res
import anysky_made_by_kmp.composeapp.generated.resources.compose_multiplatform
import kotlinx.coroutines.flow.MutableStateFlow
import dev.kako351.anysky.kmp.data.TokenDataStore
import org.koin.compose.viewmodel.koinViewModel

@Composable
@Preview
fun App(
    viewModel: MainViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()

    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = { showContent = !showContent }) {
                Text("Click me!")
            }

            Text(
                text = state,
            )
            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text("Compose: $greeting")
                }
            }
        }
    }
}

class MainViewModel(
    private val tokenDataStore: TokenDataStore
): ViewModel() {
    val state = MutableStateFlow("Hello, World")


}