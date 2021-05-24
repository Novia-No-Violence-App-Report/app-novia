package com.app.novia.core.di

import androidx.room.Room
import com.app.novia.core.data.Repository
import com.app.novia.core.data.source.local.LocalDataSource
import com.app.novia.core.data.source.local.NoviaDatabase
import com.app.novia.core.data.source.remote.RemoteDataSource
import com.app.novia.core.domain.repository.INoviaRepository
import com.salomohutapea.movieapp.core.utils.AppExecutors
import kotlinx.coroutines.DelicateCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    factory { get<NoviaDatabase>().emergencyContactDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            NoviaDatabase::class.java,
            "novia.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}

val networkModule = module {
    // TODO:  Add retrofit network handler for API request
}

@DelicateCoroutinesApi
val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<INoviaRepository> {
        Repository(
            get(),
            get(),
            get()
        )
    }
}