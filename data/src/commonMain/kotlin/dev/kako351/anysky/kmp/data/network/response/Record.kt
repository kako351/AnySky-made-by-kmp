package dev.kako351.anysky.kmp.data.network.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Record(
    @SerialName("createdAt")
    val createdAt: String,
    @SerialName("langs")
    val langs: List<String>,
    @SerialName("text")
    val text: String,
)