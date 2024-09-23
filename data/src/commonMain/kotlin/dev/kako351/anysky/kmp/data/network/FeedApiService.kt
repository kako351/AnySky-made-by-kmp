package dev.kako351.anysky.kmp.data.network

import dev.kako351.anysky.kmp.data.network.FeedApiService.Companion.GET_AUTHOR_FEED_URL
import dev.kako351.anysky.kmp.data.network.response.GetAuthorFeedResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.serialization.kotlinx.json.json

interface FeedApiService {
    companion object {
        const val GET_AUTHOR_FEED_URL = "https://public.api.bsky.app/xrpc/app.bsky.feed.getAuthorFeed?actor=kako351.bsky.social"
    }
    val client: HttpClient

    suspend fun getAuthorFeed(actor: String, token: String): GetAuthorFeedResponse
}

class FeedApiServiceImpl: FeedApiService {
    override val client = HttpClient() {
        install(ContentNegotiation) {
            json(json = kotlinx.serialization.json.Json {
                isLenient = false
                ignoreUnknownKeys = true
                allowSpecialFloatingPointValues = true
                useArrayPolymorphism = false
            })
        }
    }

    override suspend fun getAuthorFeed(actor: String, token: String): GetAuthorFeedResponse {
        val response = client.get(
            GET_AUTHOR_FEED_URL,
        ) {
            headers {
                append("Content-Type", "application/json")
                append("Accept", "application/json")
                append("Authorization", "Bearer $token")
            }
            url {
                parameters.append("actor", actor)
            }
        }
        return response.body()
    }
}
