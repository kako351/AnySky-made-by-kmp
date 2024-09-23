package dev.kako351.anysky.kmp.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import anysky_made_by_kmp.composeapp.generated.resources.Res
import anysky_made_by_kmp.composeapp.generated.resources.compose_multiplatform
import dev.kako351.anysky.kmp.Greeting
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import dev.kako351.anysky.kmp.data.model.feed.Feed

@Composable
fun FeedsScreen(
    viewModel: FeedsViewModel = koinViewModel()
) {
    MaterialTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = {
                        Text("Feeds")
                    }
                )
            }
        ) {
            Column {
                val state = viewModel.state.collectAsState().value
                when (state) {
                    is FeedsViewModel.FeedsState.Initial -> {
                        Text("Initial")
                    }

                    is FeedsViewModel.FeedsState.Loading -> {
                        Text("Loading")
                    }

                    is FeedsViewModel.FeedsState.Success -> {
                        val feeds: List<Feed> = state.uiState.feeds
                        LazyColumn {
                            items(
                                feeds
                            ) {
                                FeedItem(feed = it)
                            }
                        }

                    }

                    is FeedsViewModel.FeedsState.Error -> {
                        Text("Error")
                    }
                }

                Button(
                    onClick = {
                        viewModel.onGetFeedClicked()
                    }
                ) {
                    Text("Get Feeds")
                }
            }
        }
    }
}

@Composable
fun FeedItem(
    feed: Feed,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        Column(
            modifier = modifier.fillMaxWidth().padding(8.dp)
        ) {
            Text("@${feed.authorName}", style = MaterialTheme.typography.caption)
            Text(feed.text, style = MaterialTheme.typography.body1)
        }
        Divider(
            modifier = Modifier.fillMaxWidth(),
            color = Color.LightGray
        )
    }
}
