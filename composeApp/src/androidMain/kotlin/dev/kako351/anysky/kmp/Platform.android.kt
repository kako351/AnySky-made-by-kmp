package dev.kako351.anysky.kmp

import android.content.Context
import android.os.Build
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import dev.kako351.anysky.kmp.data.createDataStore

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

actual fun getDataStore(context: Any?): DataStore<Preferences>? = (context as? Context)?.let { createDataStore(it) }
