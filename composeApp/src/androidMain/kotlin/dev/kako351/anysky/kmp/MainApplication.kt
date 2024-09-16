package dev.kako351.anysky.kmp

import android.app.Application
import dev.kako351.anysky.kmp.di.startKoin

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin()
    }
}