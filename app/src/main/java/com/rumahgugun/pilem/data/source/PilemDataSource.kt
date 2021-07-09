package com.rumahgugun.pilem.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.rumahgugun.pilem.data.source.local.entity.MovieEntity
import com.rumahgugun.pilem.data.source.local.entity.TvEntity
import com.rumahgugun.pilem.vo.Resource

interface PilemDataSource {
    fun getAllMovies(): LiveData<Resource<PagedList<MovieEntity>>>
    fun getAllFavoriteMovies(): LiveData<PagedList<MovieEntity>>
    fun getSelectedMovie(itemName: String): LiveData<Resource<MovieEntity>>
    fun setFavoriteItem(item : MovieEntity, newState : Boolean)

    fun getAllTvs(): LiveData<Resource<PagedList<TvEntity>>>
    fun getAllFavoriteTvs(): LiveData<PagedList<TvEntity>>
    fun getSelectedTv(itemName: String): LiveData<Resource<TvEntity>>
    fun setFavoriteItem(item : TvEntity, newState : Boolean)

}