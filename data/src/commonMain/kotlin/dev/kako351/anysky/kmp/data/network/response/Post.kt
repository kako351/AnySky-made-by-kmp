package dev.kako351.anysky.kmp.data.network.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Post(
    @SerialName("author")
    val author: Author,
    @SerialName("cid")
    val cid: String,
    @SerialName("indexedAt")
    val indexedAt: String,
    @SerialName("labels")
    val labels: List<String>,
    @SerialName("likeCount")
    val likeCount: Int,
    @SerialName("quoteCount")
    val quoteCount: Int,
    @SerialName("record")
    val record: Record,
    @SerialName("replyCount")
    val replyCount: Int,
    @SerialName("repostCount")
    val repostCount: Int,
    @SerialName("uri")
    val uri: String
)