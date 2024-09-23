package dev.kako351.anysky.kmp.data.network.response

import kotlinx.serialization.Serializable

@Serializable
data class Service(
    val id: String,
    val serviceEndpoint: String,
    val type: String
)