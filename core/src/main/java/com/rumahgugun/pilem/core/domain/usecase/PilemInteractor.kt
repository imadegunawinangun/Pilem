package com.rumahgugun.pilem.core.domain.usecase

import com.rumahgugun.pilem.core.data.Resource
import com.rumahgugun.pilem.core.domain.model.Gugun
import com.rumahgugun.pilem.core.domain.model.Movie
import com.rumahgugun.pilem.core.domain.model.Tv
import com.rumahgugun.pilem.core.domain.repository.IPilemRepository
import kotlinx.coroutines.flow.Flow

class PilemInteractor(private val iPilemRepository: IPilemRepository): PilemUseCase {
    override fun getAllMovies(): Flow<Resource<List<Movie>>> {
        return iPilemRepository.getAllMovies()
    }

    override fun getAllFavoriteMovies(): Flow<List<Movie>> {
        return iPilemRepository.getAllFavoriteMovies()
    }

    override fun getSelectedMovie(itemName: String): Flow<Resource<Movie>> {
        return iPilemRepository.getSelectedMovie(itemName)
    }

    override fun setFavoriteItem(item: Movie, newState: Boolean) {
        return iPilemRepository.setFavoriteItem(item,newState)
    }

    override fun setFavoriteItem(item: Tv, newState: Boolean) {
        return iPilemRepository.setFavoriteItem(item,newState)
    }

    override fun getAllTvs(): Flow<Resource<List<Tv>>> {
        return iPilemRepository.getAllTvs()
    }

    override fun getAllFavoriteTvs(): Flow<List<Tv>> {
        return iPilemRepository.getAllFavoriteTvs()
    }

    override fun getSelectedTv(itemName: String): Flow<Resource<Tv>> {
        return iPilemRepository.getSelectedTv(itemName)
    }

    override fun getProfile(): Gugun {
        return iPilemRepository.getProfile()
    }
}