package com.rumahgugun.pilem.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rumahgugun.pilem.core.domain.model.Movie
import com.rumahgugun.pilem.core.domain.model.Tv
import com.rumahgugun.pilem.core.domain.usecase.PilemUseCase

class FavoriteViewModel(private val pilemRepository: PilemUseCase) : ViewModel() {
    fun getMovies(): LiveData<List<Movie>> =
        pilemRepository.getAllFavoriteMovies().asLiveData()

    fun getTvs(): LiveData<List<Tv>> =
        pilemRepository.getAllFavoriteTvs().asLiveData()
}