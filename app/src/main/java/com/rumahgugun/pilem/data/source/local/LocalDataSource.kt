package com.rumahgugun.pilem.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.rumahgugun.pilem.data.source.local.entity.MovieEntity
import com.rumahgugun.pilem.data.source.local.entity.TvEntity
import com.rumahgugun.pilem.data.source.local.room.PilemDao

class LocalDataSource private constructor(private val mPilemDao: PilemDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(pilemDao: PilemDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(pilemDao).apply {
                INSTANCE = this
            }

    }

    fun getAllMovies(): DataSource.Factory<Int, MovieEntity> = mPilemDao.getAllMovies()
    fun getFavoriteMovies(): DataSource.Factory<Int, MovieEntity> = mPilemDao.getFavoriteMovies()
    fun getSelectedMovie(itemName : String): LiveData<MovieEntity> = mPilemDao.getSelectedMovie(itemName)
    fun insertMovies(listMovies : List<MovieEntity>) {
        mPilemDao.insertMovies(listMovies)
    }
    fun insertMovie(item: MovieEntity){
        mPilemDao.insertMovie(item)
    }
    fun updateFavoriteMovie(item : MovieEntity, newState : Boolean) {
        item.isFavorite = newState
        mPilemDao.updateFavoriteMovie(item)
    }

/*******************************************************************************************************/

    fun getAllTvs(): DataSource.Factory<Int, TvEntity> = mPilemDao.getAllTvs()
    fun getFavoriteTvs(): DataSource.Factory<Int, TvEntity> = mPilemDao.getFavoriteTvs()
    fun getSelectedTv(itemName : String): LiveData<TvEntity> = mPilemDao.getSelectedTv(itemName)
    fun insertTvs(listTv : List<TvEntity>) {
        mPilemDao.insertTvs(listTv)
    }
    fun insertTv(item : TvEntity) {
        mPilemDao.insertTv(item)
    }
    fun updateFavoriteTv(item : TvEntity, newState : Boolean) {
        item.isFavorite = newState
        mPilemDao.updateFavoriteTvs(item)
    }





}