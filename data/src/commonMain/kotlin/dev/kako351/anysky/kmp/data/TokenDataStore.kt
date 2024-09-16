package dev.kako351.anysky.kmp.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import dev.kako351.anysky.kmp.data.TokenDataStore.Companion.ACCESS_TOKEN
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.single
import okio.Path.Companion.toPath

fun createDataStore(producePath: () -> String): DataStore<Preferences> = PreferenceDataStoreFactory.createWithPath(
    produceFile = { producePath().toPath() }
)

internal const val dataStoreFileName = "any_sky_token.preferences_pb"

interface TokenDataStore {
    companion object {
        const val ACCESS_TOKEN = "access_token"
        const val REFRESH_TOKEN = "refresh_token"
    }

    val accessToken: Flow<String>?
}

class TokenDataStoreImpl(
    private val context: Context
): TokenDataStore {
    private val dataStore: DataStore<Preferences>? = getDataStore(context)

    override val accessToken: Flow<String>? = dataStore?.data?.map {
        it[stringPreferencesKey(ACCESS_TOKEN)] ?: ""
    }
}

expect fun TokenDataStore.getDataStore(context: Any?): DataStore<Preferences>?

