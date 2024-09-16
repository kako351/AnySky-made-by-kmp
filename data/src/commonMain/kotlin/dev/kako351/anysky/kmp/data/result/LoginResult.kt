package dev.kako351.anysky.kmp.data.result

import dev.kako351.anysky.kmp.data.model.auth.Authentication

sealed class LoginResult {
    data class Success(val authentication: Authentication) : LoginResult()
    data class Error(val message: String) : LoginResult()
}
