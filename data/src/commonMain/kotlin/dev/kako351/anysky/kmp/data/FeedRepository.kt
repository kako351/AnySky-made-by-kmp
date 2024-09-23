package dev.kako351.anysky.kmp.data

import dev.kako351.anysky.kmp.data.model.feed.Feed
import dev.kako351.anysky.kmp.data.network.FeedApiService
import dev.kako351.anysky.kmp.data.result.GetAuthorFeedResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext

interface FeedRepository {
    suspend fun getAuthorFeed(): GetAuthorFeedResult
}

class FeedRepositoryImpl(
    private val feedApiService: FeedApiService,
    private val tokenDataStore: TokenDataStore
): FeedRepository {
    override suspend fun getAuthorFeed(): GetAuthorFeedResult = withContext(Dispatchers.IO) {
        return@withContext try {
            val actor: String = tokenDataStore.handle?.firstOrNull()?.value ?: throw NullPointerException("Handle is null")
            val token: String = tokenDataStore.accessToken?.firstOrNull()?.value ?: throw NullPointerException("Access token is null")

            val feeds = feedApiService.getAuthorFeed(actor, token).feed.map {
                Feed(
                    cid = it.post.cid,
                    authorName = it.post.author.handle,
                    text = it.post.record.text,
                    createdAt = it.post.record.createdAt
                )
            }

            GetAuthorFeedResult.Success(feeds)
        } catch (e: Exception) {
            GetAuthorFeedResult.Error(e.message ?: "Unknown error")
        }
    }
}
