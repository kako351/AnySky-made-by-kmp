package dev.kako351.anysky.kmp.data.network.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DidDoc(
    @SerialName("@context")
    val context: List<String>,
    val alsoKnownAs: List<String>,
    val id: String,
    val service: List<Service>,
    val verificationMethod: List<VerificationMethod>
)