package com.app.novia

import android.app.Application
import com.app.novia.core.di.databaseModule
import com.app.novia.core.di.networkModule
import com.app.novia.core.di.repositoryModule
import com.app.novia.di.useCaseModule
import com.app.novia.di.viewModelModule
import kotlinx.coroutines.DelicateCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

@DelicateCoroutinesApi
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
//                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}