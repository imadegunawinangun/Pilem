package com.rumahgugun.pilem.detail.movie

import androidx.lifecycle.*
import com.rumahgugun.pilem.core.data.Resource
import com.rumahgugun.pilem.core.domain.model.Movie
import com.rumahgugun.pilem.core.domain.usecase.PilemUseCase


class DetailMovieViewModel(private var pilemUseCase: PilemUseCase) : ViewModel() {
    private var itemName =  MutableLiveData<String>()

    fun setSelectedItem(itemName: String) {
        this.itemName.value = itemName
    }

    var getMovieItem: LiveData<Resource<Movie>> = Transformations.switchMap(itemName) {
        pilemUseCase.getSelectedMovie(it).asLiveData()
    }



    fun setBookmark() {
        val movie = getMovieItem.value
        if (movie != null) {
            val courseWithModule = movie.data

            if (courseWithModule != null) {
                val newState = !courseWithModule.isFavorite
                pilemUseCase.setFavoriteItem(courseWithModule, newState)
            }
        }
    }
}