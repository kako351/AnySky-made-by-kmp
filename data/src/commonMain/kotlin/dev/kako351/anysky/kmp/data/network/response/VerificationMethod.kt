package dev.kako351.anysky.kmp.data.network.response

import kotlinx.serialization.Serializable

@Serializable
data class VerificationMethod(
    val controller: String,
    val id: String,
    val publicKeyMultibase: String,
    val type: String
)