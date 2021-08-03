package com.rumahgugun.pilem.core.data.source.remote

import android.util.Log
import com.rumahgugun.pilem.core.data.source.remote.network.ApiResponse
import com.rumahgugun.pilem.core.data.source.remote.network.ApiResponse.*
import com.rumahgugun.pilem.core.data.source.remote.network.ApiService
import com.rumahgugun.pilem.core.data.source.remote.response.detail_movie.DetailMovieResponse
import com.rumahgugun.pilem.core.data.source.remote.response.detail_tv.DetailTvResponse
import com.rumahgugun.pilem.core.data.source.remote.response.list_movie.MovieResults
import com.rumahgugun.pilem.core.data.source.remote.response.list_tv.TvResults
import com.rumahgugun.pilem.core.utils.EspressoIdlingResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    fun getAllMovies(): Flow<ApiResponse<List<MovieResults>>> {
        EspressoIdlingResource.increment()
        val a = flow {
            try {
                val response = apiService.getPopularMovie()
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(Success(response.results))
                } else {
                    emit(Empty)
                }
            } catch (e: Exception) {
                emit(Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)

        EspressoIdlingResource.decrement()

        return a
    }

    fun getAllTvs(): Flow<ApiResponse<List<TvResults>>> {
        EspressoIdlingResource.increment()
        val a = flow {
            try {
                val response = apiService.getPopularTv()
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(Success(response.results))
                } else {
                    emit(Empty)
                }
            } catch (e: Exception) {
                emit(Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)

        EspressoIdlingResource.decrement()

        return a
    }

    fun getSelectedMovie(itemName: String): Flow<ApiResponse<DetailMovieResponse>> {
        EspressoIdlingResource.increment()
        val a = flow {
            try {
                val dataArray = apiService.getDetailMovie(itemName)
                emit(Success(dataArray))
            } catch (e: Exception) {
                emit(Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
        EspressoIdlingResource.decrement()
        return a
    }

    fun getSelectedTv(itemName: String): Flow<ApiResponse<DetailTvResponse>> {
        EspressoIdlingResource.increment()
        val a = flow {
            try {
                val dataArray = apiService.getDetailTv(itemName)
                emit(Success(dataArray))
            } catch (e: Exception) {
                emit(Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
        EspressoIdlingResource.decrement()
        return a
    }

}