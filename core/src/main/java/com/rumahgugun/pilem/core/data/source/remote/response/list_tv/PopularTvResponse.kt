package com.rumahgugun.pilem.core.data.source.remote.response.list_tv

data class PopularTvResponse(
    val page: Int,
    val results: List<TvResults>,
    val total_pages: Int,
    val total_results: Int
)