package com.suslanium.gafarov

import android.app.Application
import com.suslanium.gafarov.di.provideDataModule
import com.suslanium.gafarov.di.provideDomainModule
import com.suslanium.gafarov.di.provideNetworkModule
import com.suslanium.gafarov.di.providePresentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                provideNetworkModule(),
                provideDataModule(),
                provideDomainModule(),
                providePresentationModule()
            )
        }
    }
}