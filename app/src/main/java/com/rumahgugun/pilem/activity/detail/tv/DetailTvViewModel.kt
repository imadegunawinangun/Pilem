package com.rumahgugun.pilem.activity.detail.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.rumahgugun.pilem.data.source.PilemRepository
import com.rumahgugun.pilem.data.source.local.entity.MovieEntity
import com.rumahgugun.pilem.data.source.local.entity.TvEntity
import com.rumahgugun.pilem.utils.DataDummy
import com.rumahgugun.pilem.vo.Resource

class DetailTvViewModel(private var pilemRepository: PilemRepository) : ViewModel() {
    private var itemName =  MutableLiveData<String>()

    fun setSelectedItem(itemName: String) {
        this.itemName.value = itemName
    }

    var getTvItem: LiveData<Resource<TvEntity>> = Transformations.switchMap(itemName) {
        pilemRepository.getSelectedTv(it)
    }



    fun setBookmark() {
        val movie = getTvItem.value
        if (movie != null) {
            val courseWithModule = movie.data

            if (courseWithModule != null) {
                val newState = !courseWithModule.isFavorite
                pilemRepository.setFavoriteItem(courseWithModule, newState)
            }
        }
    }
}