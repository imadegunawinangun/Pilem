package com.rumahgugun.pilem.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rumahgugun.pilem.core.data.Resource
import com.rumahgugun.pilem.core.domain.model.Movie
import com.rumahgugun.pilem.core.domain.model.Tv
import com.rumahgugun.pilem.core.domain.usecase.PilemUseCase

class MainViewModel(private val pilemRepository: PilemUseCase) : ViewModel() {
    fun getTvs(): LiveData<Resource<List<Tv>>> =
        pilemRepository.getAllTvs().asLiveData()
    fun getMovies(): LiveData<Resource<List<Movie>>> =
        pilemRepository.getAllMovies().asLiveData()
}