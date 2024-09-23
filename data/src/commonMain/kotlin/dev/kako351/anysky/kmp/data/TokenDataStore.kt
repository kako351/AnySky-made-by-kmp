package dev.kako351.anysky.kmp.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import dev.kako351.anysky.kmp.data.TokenDataStore.Companion.ACCESS_TOKEN
import dev.kako351.anysky.kmp.data.TokenDataStore.Companion.HANDLE
import dev.kako351.anysky.kmp.data.TokenDataStore.Companion.REFRESH_TOKEN
import dev.kako351.anysky.kmp.data.model.auth.AccessToken
import dev.kako351.anysky.kmp.data.model.auth.Handle
import dev.kako351.anysky.kmp.data.model.auth.RefreshToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import okio.Path.Companion.toPath

fun createDataStore(producePath: () -> String): DataStore<Preferences> = PreferenceDataStoreFactory.createWithPath(
    produceFile = { producePath().toPath() }
)

internal const val dataStoreFileName = "any_sky_token.preferences_pb"

interface TokenDataStore {
    companion object {
        const val ACCESS_TOKEN = "access_token"
        const val REFRESH_TOKEN = "refresh_token"
        const val HANDLE = "handle"
    }

    val accessToken: Flow<AccessToken>?
    val handle: Flow<Handle>?

    suspend fun saveAccessToken(token: AccessToken)
    suspend fun saveRefreshToken(token: RefreshToken)
    suspend fun saveHandle(handle: Handle)
}

internal class TokenDataStoreImpl(
    private val context: Context
): TokenDataStore {
    private val dataStore: DataStore<Preferences>? = getDataStore(context)

    override val accessToken: Flow<AccessToken>? = dataStore?.data?.map {
        AccessToken(
            value = it[stringPreferencesKey(ACCESS_TOKEN)] ?: ""
        )
    }

    override val handle: Flow<Handle>? = dataStore?.data?.map {
        Handle(
            value = it[stringPreferencesKey(HANDLE)] ?: ""
        )
    }

    override suspend fun saveAccessToken(token: AccessToken) {
        dataStore?.edit {
            it[stringPreferencesKey(ACCESS_TOKEN)] = token.value
        }
    }

    override suspend fun saveRefreshToken(token: RefreshToken) {
        dataStore?.edit {
            it[stringPreferencesKey(REFRESH_TOKEN)] = token.value
        }
    }

    override suspend fun saveHandle(handle: Handle) {
        dataStore?.edit {
            it[stringPreferencesKey(HANDLE)] = handle.value
        }
    }
}

expect fun TokenDataStore.getDataStore(context: Any?): DataStore<Preferences>?

