package dev.kako351.anysky.kmp

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

