package dev.kako351.anysky.kmp.data.di

import dev.kako351.anysky.kmp.data.TokenDataStore
import dev.kako351.anysky.kmp.data.TokenDataStoreImpl
import org.koin.dsl.module

val dataStoreModule = module {
    single<TokenDataStore> { TokenDataStoreImpl(get()) }
}