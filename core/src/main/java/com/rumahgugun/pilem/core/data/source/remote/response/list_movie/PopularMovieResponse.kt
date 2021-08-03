package com.rumahgugun.pilem.core.data.source.remote.response.list_movie

data class PopularMovieResponse(
    val page: Int,
    val results: List<MovieResults>,
    val total_pages: Int,
    val total_results: Int
)