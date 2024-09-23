package dev.kako351.anysky.kmp.data.network.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Author(
    @SerialName("associated")
    val associated: Associated,
    @SerialName("createdAt")
    val createdAt: String,
    @SerialName("did")
    val did: String,
    @SerialName("handle")
    val handle: String,
    @SerialName("labels")
    val labels: List<String>
)