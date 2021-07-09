package com.rumahgugun.pilem.activity.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.rumahgugun.pilem.data.source.PilemRepository
import com.rumahgugun.pilem.data.source.local.entity.MovieEntity
import com.rumahgugun.pilem.data.source.local.entity.TvEntity
import com.rumahgugun.pilem.vo.Resource

class FavoriteViewModel (private val pilemRepository: PilemRepository) : ViewModel() {
    fun getMovies(): LiveData<PagedList<MovieEntity>> =
        pilemRepository.getAllFavoriteMovies()

    fun getTvs(): LiveData<PagedList<TvEntity>> =
        pilemRepository.getAllFavoriteTvs()
}