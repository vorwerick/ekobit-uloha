package com.example.ekobituloha

import android.app.Application
import com.example.ekobituloha.core.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class EkobitApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        //Service locator
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@EkobitApplication)
            modules(appModule)
        }
    }
}
