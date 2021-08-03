package com.rumahgugun.pilem.core.data.source.remote.network

import com.rumahgugun.pilem.core.data.source.remote.response.detail_movie.DetailMovieResponse
import com.rumahgugun.pilem.core.data.source.remote.response.detail_tv.DetailTvResponse
import com.rumahgugun.pilem.core.data.source.remote.response.list_movie.PopularMovieResponse
import com.rumahgugun.pilem.core.data.source.remote.response.list_tv.PopularTvResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("movie/popular?api_key=7f8b242fa009775f05eca3cd6889367a&language=en-US&page=1")
    suspend fun getPopularMovie(): PopularMovieResponse

    //https://api.themoviedb.org/3/movie/550?api_key=7f8b242fa009775f05eca3cd6889367a&language=en-US
    @GET("movie/{id}?api_key=7f8b242fa009775f05eca3cd6889367a&language=en-US")
    suspend fun getDetailMovie(@Path("id") id: String): DetailMovieResponse

    @GET("tv/popular?api_key=7f8b242fa009775f05eca3cd6889367a&language=en-US&page=1")
    suspend fun getPopularTv(): PopularTvResponse

    //https://api.themoviedb.org/3/tv/84958?api_key=7f8b242fa009775f05eca3cd6889367a&language=en-US
    @GET("tv/{id}?api_key=7f8b242fa009775f05eca3cd6889367a&language=en-US")
    suspend fun getDetailTv(@Path("id") id: String): DetailTvResponse


}
