package com.rumahgugun.pilem.di

import android.content.Context
import com.rumahgugun.pilem.data.source.PilemRepository
import com.rumahgugun.pilem.data.source.local.LocalDataSource
import com.rumahgugun.pilem.data.source.local.room.PilemDatabase
import com.rumahgugun.pilem.data.source.remote.RemoteDataSource
import com.rumahgugun.pilem.utils.AppExecutors
import com.rumahgugun.pilem.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): PilemRepository {

        val database = PilemDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        val localDataSource = LocalDataSource.getInstance(database.pilemDao())
        val appExecutors = AppExecutors()

        return PilemRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}
