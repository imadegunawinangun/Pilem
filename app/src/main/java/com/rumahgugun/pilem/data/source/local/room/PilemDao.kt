package com.rumahgugun.pilem.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.rumahgugun.pilem.data.source.local.entity.MovieEntity
import com.rumahgugun.pilem.data.source.local.entity.TvEntity

@Dao
interface PilemDao {

    @Query("SELECT * FROM tb_favorite_movie")
    fun getAllMovies(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM tb_favorite_movie where isFavorite = 1")
    fun getFavoriteMovies(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM tb_favorite_movie where name = :nameItem")
    fun getSelectedMovie(nameItem : String): LiveData<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(listMovies : List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(item : MovieEntity)

    @Update
    fun updateFavoriteMovie(movie : MovieEntity)

    /******************************************************************/

    @Query("SELECT * FROM tb_favorite_tv")
    fun getAllTvs(): DataSource.Factory<Int, TvEntity>

    @Query("SELECT * FROM tb_favorite_tv where isFavorite = 1")
    fun getFavoriteTvs(): DataSource.Factory<Int, TvEntity>

    @Query("SELECT * FROM tb_favorite_tv where name = :nameItem")
    fun getSelectedTv(nameItem : String): LiveData<TvEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvs(listTv : List<TvEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTv(item : TvEntity)

    @Update
    fun updateFavoriteTvs(tv : TvEntity)
}