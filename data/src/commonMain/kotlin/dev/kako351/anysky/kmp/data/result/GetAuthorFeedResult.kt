package dev.kako351.anysky.kmp.data.result

import dev.kako351.anysky.kmp.data.model.feed.Feed

sealed class GetAuthorFeedResult {
    data class Success(val feeds: List<Feed>) : GetAuthorFeedResult()
    data class Error(val message: String) : GetAuthorFeedResult()

}