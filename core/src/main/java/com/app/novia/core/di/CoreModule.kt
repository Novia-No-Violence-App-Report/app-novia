package com.app.novia.core.di

import androidx.room.Room
import com.app.novia.core.Repository
import com.app.novia.core.data.source.local.LocalDataSource
import com.app.novia.core.data.source.local.NoviaDatabase
import com.app.novia.core.data.source.remote.ApiService
import com.app.novia.core.data.source.remote.RemoteDataSource
import com.app.novia.core.domain.repository.INoviaRepository
import com.app.novia.core.utils.AppExecutors
import kotlinx.coroutines.DelicateCoroutinesApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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
    single {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://34.101.116.82/api/")
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(ApiService::class.java)
    }
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