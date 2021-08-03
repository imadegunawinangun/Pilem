package com.rumahgugun.pilem.core.domain.usecase

import com.rumahgugun.pilem.core.data.Resource
import com.rumahgugun.pilem.core.domain.model.Gugun
import com.rumahgugun.pilem.core.domain.model.Movie
import com.rumahgugun.pilem.core.domain.model.Tv
import kotlinx.coroutines.flow.Flow

interface PilemUseCase {
    fun getAllMovies(): Flow<Resource<List<Movie>>>
    fun getAllFavoriteMovies(): Flow<List<Movie>>
    fun getSelectedMovie(itemName: String): Flow<Resource<Movie>>
    fun setFavoriteItem(item : Movie, newState : Boolean)

    fun getAllTvs(): Flow<Resource<List<Tv>>>
    fun getAllFavoriteTvs(): Flow<List<Tv>>
    fun getSelectedTv(itemName: String): Flow<Resource<Tv>>
    fun setFavoriteItem(item : Tv, newState : Boolean)

    fun getProfile(): Gugun
}