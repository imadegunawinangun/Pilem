package com.rumahgugun.pilem

import android.app.Application
import com.rumahgugun.pilem.core.di.databaseModule
import com.rumahgugun.pilem.core.di.networkModule
import com.rumahgugun.pilem.core.di.repositoryModule
import com.rumahgugun.pilem.di.useCaseModule
import com.rumahgugun.pilem.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}