package dev.kako351.anysky.kmp.data

import dev.kako351.anysky.kmp.data.model.auth.AccessToken
import dev.kako351.anysky.kmp.data.model.auth.Authentication
import dev.kako351.anysky.kmp.data.model.auth.RefreshToken
import dev.kako351.anysky.kmp.data.network.AuthApiService
import dev.kako351.anysky.kmp.data.result.LoginResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface AuthRepository {
    suspend fun login(email: String, password: String): LoginResult
}

internal class AuthRepositoryImpl(
    private val authApiService: AuthApiService,
    private val tokenDataStore: TokenDataStore
): AuthRepository {
    override suspend fun login(email: String, password: String): LoginResult = withContext(Dispatchers.IO) {
        return@withContext try {
            val response = authApiService.createSession(identifier = email, password = password)

            val authentication = Authentication(
                accessToken = AccessToken(response.accessJwt),
                refreshToken = RefreshToken(response.refreshJwt)
            )

            tokenDataStore.saveAccessToken(AccessToken(response.accessJwt))
            tokenDataStore.saveRefreshToken(RefreshToken(response.refreshJwt))

            LoginResult.Success(authentication = authentication)
        } catch (e: Exception) {
            LoginResult.Error(e.message ?: "Unknown error")
        }
    }
}