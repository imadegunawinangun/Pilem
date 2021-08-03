package com.rumahgugun.pilem.core.di

import androidx.room.Room
import com.rumahgugun.pilem.core.data.source.local.room.PilemDatabase
import com.rumahgugun.pilem.core.data.source.remote.RemoteDataSource
import com.rumahgugun.pilem.core.data.source.remote.network.ApiService
import com.rumahgugun.pilem.core.domain.repository.IPilemRepository
import com.rumahgugun.pilem.core.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<PilemDatabase>().pilemDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            PilemDatabase::class.java, "Pilem.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}


val repositoryModule = module {
    single { com.rumahgugun.pilem.core.data.source.local.LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IPilemRepository> { com.rumahgugun.pilem.core.data.PilemRepository(get(), get(), get()) }
}