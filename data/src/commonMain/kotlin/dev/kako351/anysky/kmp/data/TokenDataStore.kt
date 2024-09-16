package dev.kako351.anysky.kmp.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import okio.Path.Companion.toPath

fun createDataStore(producePath: () -> String): DataStore<Preferences> = PreferenceDataStoreFactory.createWithPath(
    produceFile = { producePath().toPath() }
)

internal const val dataStoreFileName = "any_sky_token.preferences_pb"

interface TokenDataStore {

}

class TokenDataStoreImpl: TokenDataStore {
    private val dataStore = createDataStore { dataStoreFileName }

}

