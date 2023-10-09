package com.example.appy

import android.app.Application
import com.example.appy.main.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber
import timber.log.Timber.DebugTree


class App : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(DebugTree())

        startKoin {
            androidContext(this@App)
            modules(appModule())
        }
    }
}