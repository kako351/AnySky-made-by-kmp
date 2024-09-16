package dev.kako351.anysky.kmp

import android.app.Application
import dev.kako351.anysky.kmp.di.startKoin
import org.koin.android.ext.koin.androidContext

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(
            appDeclaration = {
                androidContext(this@MainApplication)
            }
        )
    }
}
