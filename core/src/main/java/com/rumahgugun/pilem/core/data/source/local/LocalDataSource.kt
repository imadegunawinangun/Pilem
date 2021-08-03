package com.rumahgugun.pilem.core.data.source.local

import com.rumahgugun.pilem.core.data.source.local.entity.GugunEntity
import com.rumahgugun.pilem.core.data.source.local.entity.MovieEntity
import com.rumahgugun.pilem.core.data.source.local.entity.TvEntity
import com.rumahgugun.pilem.core.data.source.local.room.PilemDao
import com.rumahgugun.pilem.core.domain.model.Gugun
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val mPilemDao: PilemDao) {

    fun getAllMovies(): Flow<List<MovieEntity>> = mPilemDao.getAllMovies()
    fun getFavoriteMovies(): Flow<List<MovieEntity>> = mPilemDao.getFavoriteMovies()
    fun getSelectedMovie(itemName: String): Flow<MovieEntity> = mPilemDao.getSelectedMovie(itemName)
    suspend fun insertMovies(listMovies: List<MovieEntity>) {
        mPilemDao.insertMovies(listMovies)
    }

    suspend fun insertMovie(item: MovieEntity) {
        mPilemDao.insertMovie(item)
    }

    fun updateFavoriteMovie(item: MovieEntity, newState: Boolean) {
        item.isFavorite = newState
        mPilemDao.updateFavoriteMovie(item)
    }

    /*******************************************************************************************************/

    fun getAllTvs(): Flow<List<TvEntity>> = mPilemDao.getAllTvs()
    fun getFavoriteTvs(): Flow<List<TvEntity>> = mPilemDao.getFavoriteTvs()
    fun getSelectedTv(itemName: String): Flow<TvEntity> = mPilemDao.getSelectedTv(itemName)
    suspend fun insertTvs(listTv: List<TvEntity>) {
        mPilemDao.insertTvs(listTv)
    }

    suspend fun insertTv(item: TvEntity) {
        mPilemDao.insertTv(item)
    }

    fun updateFavoriteTv(item: TvEntity, newState: Boolean) {
        item.isFavorite = newState
        mPilemDao.updateFavoriteTvs(item)
    }

    fun getProfil(): GugunEntity {
        return mPilemDao.getProfil()
    }


}