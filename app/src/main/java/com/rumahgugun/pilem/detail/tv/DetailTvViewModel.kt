package com.rumahgugun.pilem.detail.tv

import android.util.Log
import androidx.lifecycle.*
import com.rumahgugun.pilem.core.data.Resource
import com.rumahgugun.pilem.core.domain.model.Tv
import com.rumahgugun.pilem.core.domain.usecase.PilemUseCase

class DetailTvViewModel(private var pilemUseCase: PilemUseCase) : ViewModel() {
    private var itemName =  MutableLiveData<String>()

    fun setSelectedItem(itemName: String) {
        this.itemName.value = itemName
    }

    var getTvItem: LiveData<Resource<Tv>> = Transformations.switchMap(itemName) {
        Log.d("DetailTvViewModel",it+"kuda")
        pilemUseCase.getSelectedTv(it).asLiveData()
    }



    fun setBookmark() {
        val movie = getTvItem.value
        if (movie != null) {
            val courseWithModule = movie.data

            if (courseWithModule != null) {
                val newState = !courseWithModule.isFavorite
                pilemUseCase.setFavoriteItem(courseWithModule, newState)
            }
        }
    }
}