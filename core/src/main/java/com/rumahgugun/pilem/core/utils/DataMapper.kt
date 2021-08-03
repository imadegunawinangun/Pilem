package com.rumahgugun.pilem.core.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations.map
import com.rumahgugun.pilem.core.data.source.local.entity.GugunEntity
import com.rumahgugun.pilem.core.data.source.local.entity.MovieEntity
import com.rumahgugun.pilem.core.data.source.local.entity.TvEntity
import com.rumahgugun.pilem.core.data.source.remote.response.detail_movie.DetailMovieResponse
import com.rumahgugun.pilem.core.data.source.remote.response.detail_tv.DetailTvResponse
import com.rumahgugun.pilem.core.data.source.remote.response.list_movie.MovieResults
import com.rumahgugun.pilem.core.data.source.remote.response.list_tv.TvResults
import com.rumahgugun.pilem.core.domain.model.Gugun
import com.rumahgugun.pilem.core.domain.model.Movie
import com.rumahgugun.pilem.core.domain.model.Tv
import org.w3c.dom.Entity

object DataMapper {
    fun GugunEntity.asDomain(): Gugun {
        return Gugun(
            nama = "Gugun",
        status = "RumahGugun",
        email = "rumahgugun@gmail.com"
        )
    }


    fun List<MovieResults>.asMovieEntities(): List<MovieEntity>{
        return map{
            MovieEntity(
                it.id,
                it.poster_path,
                it.original_title,
                it.overview,
                it.release_date,
                it.vote_average,
                false
            )
        }
    }
/*
    fun DataSource.Factory<Int, MovieEntity>.asMovieDomain(): DataSource.Factory<Int, Movie>{
        return map{
            Movie(
                it.id,
                it.picture,
                it.name,
                it.description,
                it.duration,
                it.rating,
                isFavorite = it.isFavorite
            )
        }
    }
*/
    fun Movie.asMovieEntity(): MovieEntity{
        return MovieEntity(
            id,
            picture,
            name,
            description,
            duration,
            rating,
            isFavorite = isFavorite
        )
    }
    fun MovieEntity.asMovieDomain(): Movie{
        return Movie(
            id,
            picture,
            name,
            description,
            duration,
            rating,
            isFavorite = isFavorite
        )
    }
    fun DetailMovieResponse.asMovieEntity(): MovieEntity{
        return MovieEntity(
            id,
            backdrop_path,
            original_title,
            overview,
            status,
            vote_average,
            false
        )
    }
    fun List<MovieEntity>.asDomain(): List<Movie>{
        return map{
            Movie(
                it.id,
                it.picture,
                it.name,
                it.description,
                it.duration,
                it.rating,
                isFavorite = it.isFavorite
            )
        }
    }
    fun mapMovieDomainToEntity(input: Movie): MovieEntity {

        return MovieEntity(
            input.id,
            input.picture,
            input.name,
            input.description,
            input.duration,
            input.rating,
            isFavorite = input.isFavorite
        )
    }
    fun mapMovieEntityToDomain(input: MovieEntity): Movie {

        return Movie(
            input.id,
            input.picture,
            input.name,
            input.description,
            input.duration,
            input.rating,
            isFavorite = input.isFavorite
        )
    }
    fun mapMovieResponseToEntity(input: DetailMovieResponse): MovieEntity {

        return MovieEntity(
            input.id,
            input.backdrop_path,
            input.original_title,
            input.overview,
            input.status,
            input.vote_average,
            false
        )
    }
    fun mapMovieResponsesToEntities(input: List<MovieResults>): List<MovieEntity> {
        val list = ArrayList<MovieEntity>()
        input.map { response ->
            val entity = MovieEntity(
                response.id,
                response.poster_path,
                response.original_title,
                response.overview,
                response.release_date,
                response.vote_average,
                false
            )
            list.add(entity)
        }
        return list
    }
    fun mapMovieEntitiesToDomains(input: List<MovieEntity>): List<Movie> {
        val list = ArrayList<Movie>()
        input.map {
            val entity = Movie(
                it.id,
                it.picture,
                it.name,
                it.description,
                it.duration,
                it.rating,
                isFavorite = it.isFavorite
            )
            list.add(entity)
        }
        return list
    }


    /***************************************************/
    fun List<TvResults>.asTvEntities(): List<TvEntity>{
        return map{
            TvEntity(
                it.id,
                it.poster_path,
                it.name,
                it.overview,
                it.first_air_date,
                it.vote_average,
                isFavorite = false
            )
        }
    }
/*
    fun DataSource.Factory<Int, TvEntity>.asTvDomain(): DataSource.Factory<Int, Tv>{
        return map{
            Tv(
                it.id,
                it.picture,
                it.name,
                it.description,
                it.duration,
                it.rating,
                isFavorite = it.isFavorite
            )
        }
    }
*/
    fun List<TvEntity>.asTvDomain(): List<Tv>{
        return map{
            Tv(
                it.id,
                it.picture,
                it.name,
                it.description,
                it.duration,
                it.rating,
                isFavorite = it.isFavorite
            )
        }
    }
    fun TvEntity.asTvDomain(): Tv{
        return Tv(
            id,
            picture,
            name,
            description,
            duration,
            rating,
            isFavorite = isFavorite
        )
    }
    fun Tv.asTvEntity(): TvEntity{
        return TvEntity(
            id,
            picture,
            name,
            description,
            duration,
            rating,
            isFavorite = isFavorite
        )
    }
    fun DetailTvResponse.asTvEntity(): TvEntity{
        return TvEntity(
            id,
            backdrop_path,
            original_name,
            overview,
            status,
            vote_average,
            false
        )
    }

    fun mapTvResponsesToEntities(input: List<TvResults>): List<TvEntity> {
        val list = ArrayList<TvEntity>()
        input.map {
            val entity = TvEntity(
                it.id,
                it.poster_path,
                it.name,
                it.overview,
                it.first_air_date,
                it.vote_average,
                isFavorite = false
            )
            list.add(entity)
        }
        return list
    }

/*
    fun mapTvEntitiesToDomains(input: DataSource.Factory<Int, TvEntity>): DataSource.Factory<Int, Tv> {
        input.map {
            val entity = Tv(
                it.id,
                it.picture,
                it.name,
                it.description,
                it.duration,
                it.rating,
                isFavorite = it.isFavorite
            )
            list.add(entity)
        }
        return
    }
*/
    fun mapTvDomainToEntity(input: Tv): TvEntity {

        return TvEntity(
            input.id,
            input.picture,
            input.name,
            input.description,
            input.duration,
            input.rating,
            isFavorite = input.isFavorite
        )
    }
    fun mapTvEntityToDomain(input: TvEntity): Tv {

        return Tv(
            input.id,
            input.picture,
            input.name,
            input.description,
            input.duration,
            input.rating,
            isFavorite = input.isFavorite
        )
    }

    fun mapTvResponseToEntity(data: DetailTvResponse): TvEntity {

        return TvEntity(
            data.id,
            data.backdrop_path,
            data.original_name,
            data.overview,
            data.status,
            data.vote_average,
            false
        )
    }


}
