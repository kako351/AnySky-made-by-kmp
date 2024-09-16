package dev.kako351.anysky.kmp.di

import org.koin.core.context.startKoin
import dev.kako351.anysky.kmp.data.di.dataStoreModule

fun startKoin() = startKoin {
    modules(
        dataStoreModule,
    )
}