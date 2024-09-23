package dev.kako351.anysky.kmp.data.model.auth

data class Authentication(
    val accessToken: AccessToken,
    val refreshToken: RefreshToken,
    val handle: Handle
)
