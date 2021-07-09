package com.rumahgugun.pilem.activity.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.rumahgugun.pilem.data.source.PilemRepository
import com.rumahgugun.pilem.data.source.local.entity.MovieEntity
import com.rumahgugun.pilem.data.source.local.entity.TvEntity
import com.rumahgugun.pilem.vo.Resource

class MainViewModel (private val pilemRepository: PilemRepository) : ViewModel() {
    fun getTvs(): LiveData<Resource<PagedList<TvEntity>>> =
        pilemRepository.getAllTvs()
    fun getMovies(): LiveData<Resource<PagedList<MovieEntity>>> =
        pilemRepository.getAllMovies()
}