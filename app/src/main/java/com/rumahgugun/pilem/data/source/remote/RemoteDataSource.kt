package com.rumahgugun.pilem.data.source.remote

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rumahgugun.pilem.data.source.remote.response.ListMovieResponse
import com.rumahgugun.pilem.data.source.remote.response.ListTvResponse
import com.rumahgugun.pilem.data.source.remote.response.MovieResponse
import com.rumahgugun.pilem.data.source.remote.response.TvResponse
import com.rumahgugun.pilem.utils.EspressoIdlingResource
import com.rumahgugun.pilem.utils.JsonHelper

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {
    private val handler = Handler(Looper.getMainLooper())

    companion object {

        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource =
            instance ?: synchronized(this) {
                RemoteDataSource(helper).apply { instance = this }
            }
    }

    fun getAllMovies():LiveData<ApiResponse<List<MovieResponse>>>{
        val listItem = MutableLiveData<ApiResponse<List<MovieResponse>>>()
        EspressoIdlingResource.increment()
        handler.postDelayed({
            listItem.value = ApiResponse.success(jsonHelper.getMovieJson().itemMovies)
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
        return listItem
    }

    fun getAllTvs():LiveData<ApiResponse<List<TvResponse>>>{
        val listItem = MutableLiveData<ApiResponse<List<TvResponse>>>()
        EspressoIdlingResource.increment()
        handler.postDelayed({
            listItem.value = ApiResponse.success(jsonHelper.getTvJson().itemShows)
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
        return listItem
    }

    fun getSelectedMovie(itemName : String):LiveData<ApiResponse<MovieResponse>>{
        val item = MutableLiveData<ApiResponse<MovieResponse>>()
        EspressoIdlingResource.increment()
        handler.postDelayed({
            item.value = ApiResponse.success(jsonHelper.getSelectedMovie(itemName))
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
        return item
    }

    fun getSelectedTv(itemName : String):LiveData<ApiResponse<TvResponse>>{
        val item = MutableLiveData<ApiResponse<TvResponse>>()
        EspressoIdlingResource.increment()
        handler.postDelayed({
            item.value = ApiResponse.success(jsonHelper.getSelectedTv(itemName))
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
        return item
    }

}