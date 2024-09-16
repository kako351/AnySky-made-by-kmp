package dev.kako351.anysky.kmp.data.di

import dev.kako351.anysky.kmp.data.AuthRepository
import dev.kako351.anysky.kmp.data.AuthRepositoryImpl
import dev.kako351.anysky.kmp.data.TokenDataStore
import dev.kako351.anysky.kmp.data.TokenDataStoreImpl
import dev.kako351.anysky.kmp.data.network.AuthApiService
import dev.kako351.anysky.kmp.data.network.AuthApiServiceImpl
import org.koin.core.scope.get
import org.koin.dsl.module

val dataStoreModule = module {
    single<TokenDataStore> { TokenDataStoreImpl(get()) }
    single<AuthApiService> { AuthApiServiceImpl() }
    single<AuthRepository> { AuthRepositoryImpl(get()) }
}