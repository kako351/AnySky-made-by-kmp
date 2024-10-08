package dev.kako351.anysky.kmp.data.network

import dev.kako351.anysky.kmp.data.network.AuthApiService.Companion.CREATE_SESSION_URL
import dev.kako351.anysky.kmp.data.network.response.CreateSessionResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.parameters
import io.ktor.serialization.kotlinx.json.json

interface AuthApiService {
    companion object {
        const val CREATE_SESSION_URL = "https://bsky.social/xrpc/com.atproto.server.createSession"
    }
    val client: HttpClient

    suspend fun createSession(identifier: String, password: String): CreateSessionResponse
}

internal class AuthApiServiceImpl: AuthApiService {
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

    override suspend fun createSession(identifier: String, password: String): CreateSessionResponse {
        val response = client.post(
            CREATE_SESSION_URL,
        ) {
            headers {
                append("Content-Type", "application/json")
                append("Accept", "application/json")
            }
            setBody(mapOf("identifier" to identifier, "password" to password))
        }
        return response.body()
    }
}
