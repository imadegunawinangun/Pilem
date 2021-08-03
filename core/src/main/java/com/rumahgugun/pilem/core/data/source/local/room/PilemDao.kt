package com.rumahgugun.pilem.core.data.source.local.room

import androidx.room.*
import com.rumahgugun.pilem.core.data.source.local.entity.GugunEntity
import com.rumahgugun.pilem.core.data.source.local.entity.MovieEntity
import com.rumahgugun.pilem.core.data.source.local.entity.TvEntity
import com.rumahgugun.pilem.core.domain.model.Gugun
import kotlinx.coroutines.flow.Flow

@Dao
interface PilemDao {

    @Query("SELECT * FROM tb_favorite_movie")
    fun getAllMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM tb_favorite_movie where isFavorite = 1")
    fun getFavoriteMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM tb_favorite_movie where id = :nameItem")
    fun getSelectedMovie(nameItem : String): Flow<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(listMovies : List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(item : MovieEntity)

    @Update
    fun updateFavoriteMovie(movie : MovieEntity)

    /******************************************************************/

    @Query("SELECT * FROM tb_favorite_tv")
    fun getAllTvs(): Flow<List<TvEntity>>

    @Query("SELECT * FROM tb_favorite_tv where isFavorite = 1")
    fun getFavoriteTvs(): Flow<List<TvEntity>>

    @Query("SELECT * FROM tb_favorite_tv where id = :nameItem")
    fun getSelectedTv(nameItem : String): Flow<TvEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvs(listTv : List<TvEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTv(item : TvEntity)

    @Update
    fun updateFavoriteTvs(tv : TvEntity)

    fun getProfil(): GugunEntity {
        return GugunEntity()
    }
}