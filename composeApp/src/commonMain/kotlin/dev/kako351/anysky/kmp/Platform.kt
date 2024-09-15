package dev.kako351.anysky.kmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform