package dev.kako351.anysky.kmp.data.network.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetAuthorFeedResponse(
    @SerialName("feed")
    val feed: List<Feed>
)