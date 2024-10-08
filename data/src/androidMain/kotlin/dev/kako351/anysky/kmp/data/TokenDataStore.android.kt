package dev.kako351.anysky.kmp.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

fun createDataStore(context: Context): DataStore<Preferences> = createDataStore(
    producePath = { context.filesDir.resolve(dataStoreFileName).absolutePath }
)

actual fun TokenDataStore.getDataStore(context: Any?) : DataStore<Preferences>? = (context as? Context)?.let { createDataStore(it) }
