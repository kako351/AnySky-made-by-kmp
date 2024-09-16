package dev.kako351.anysky.kmp.data.network.response

data class CreateSessionResponse(
    val accessJwt: String,
    val active: Boolean,
    val did: String,
    val didDoc: DidDoc,
    val email: String,
    val emailAuthFactor: Boolean,
    val emailConfirmed: Boolean,
    val handle: String,
    val refreshJwt: String,
    val status: String
)