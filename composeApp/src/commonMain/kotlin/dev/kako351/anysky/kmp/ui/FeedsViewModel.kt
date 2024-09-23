package dev.kako351.anysky.kmp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.kako351.anysky.kmp.data.FeedRepository
import kotlinx.coroutines.launch
import dev.kako351.anysky.kmp.data.model.feed.Feed
import dev.kako351.anysky.kmp.data.result.GetAuthorFeedResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class FeedsViewModel(
    private val feedRepository: FeedRepository,
): ViewModel()  {
    private val _state = MutableStateFlow<FeedsState>(FeedsState.Initial)
    val state = _state.asStateFlow()

    init {
        getFeeds()
    }

    fun onGetFeedClicked() {
        getFeeds()
    }

    private fun getFeeds() {
        viewModelScope.launch {
            _state.update {
                FeedsState.Loading
            }
            val result = feedRepository.getAuthorFeed()
            when(result) {
                is GetAuthorFeedResult.Success -> onGetFeedsSuccess(result.feeds)
                is GetAuthorFeedResult.Error -> onGetFeedsError(result.message)
            }
        }
    }

    private fun onGetFeedsSuccess(feeds: List<Feed>) {
        _state.update {
            FeedsState.Success(FeedsUiState(feeds))
        }
    }

    private fun onGetFeedsError(message: String) {
        _state.update {
            FeedsState.Error(message)
        }
    }

    sealed class FeedsState {
        data object Initial: FeedsState()
        data object Loading: FeedsState()
        data class Success(val uiState: FeedsUiState): FeedsState()
        data class Error(val message: String): FeedsState()
    }

    data class FeedsUiState(
        val feeds: List<Feed> = emptyList()
    )
}