package dev.kako351.anysky.kmp.di

import org.koin.core.context.startKoin
import dev.kako351.anysky.kmp.data.di.dataStoreModule
import org.koin.dsl.KoinAppDeclaration

fun startKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(
        dataStoreModule,
    )
}