package com.sweta.democleanarchitecture

import android.app.Application
import com.sweta.data.di.apiModule
import com.sweta.data.di.repositoryModule
import com.sweta.data.di.useCaseModule
import com.sweta.democleanarchitecture.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class DemoApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
           //Timber.plant(Timber.DebugTree())
        }
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@DemoApplication)
            modules(
                apiModule,
                repositoryModule,
                useCaseModule,
                viewModelModule
            )
        }


    }
}