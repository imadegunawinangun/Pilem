package com.rumahgugun.pilem.core.data

import com.rumahgugun.pilem.core.data.source.remote.RemoteDataSource
import com.rumahgugun.pilem.core.data.source.remote.network.ApiResponse
import com.rumahgugun.pilem.core.data.source.remote.response.detail_movie.DetailMovieResponse
import com.rumahgugun.pilem.core.data.source.remote.response.detail_tv.DetailTvResponse
import com.rumahgugun.pilem.core.data.source.remote.response.list_movie.MovieResults
import com.rumahgugun.pilem.core.data.source.remote.response.list_tv.TvResults
import com.rumahgugun.pilem.core.domain.model.Gugun
import com.rumahgugun.pilem.core.domain.model.Movie
import com.rumahgugun.pilem.core.domain.model.Tv
import com.rumahgugun.pilem.core.domain.repository.IPilemRepository
import com.rumahgugun.pilem.core.utils.AppExecutors
import com.rumahgugun.pilem.core.utils.DataMapper.asDomain
import com.rumahgugun.pilem.core.utils.DataMapper.asMovieDomain
import com.rumahgugun.pilem.core.utils.DataMapper.asMovieEntities
import com.rumahgugun.pilem.core.utils.DataMapper.asMovieEntity
import com.rumahgugun.pilem.core.utils.DataMapper.asTvDomain
import com.rumahgugun.pilem.core.utils.DataMapper.asTvEntities
import com.rumahgugun.pilem.core.utils.DataMapper.asTvEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PilemRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: com.rumahgugun.pilem.core.data.source.local.LocalDataSource,
    private val appExecutors: AppExecutors
) : IPilemRepository {

    override fun getAllMovies(): Flow<Resource<List<Movie>>> {
        return object :
            com.rumahgugun.pilem.core.data.NetworkBoundResource<List<Movie>, List<MovieResults>>(appExecutors) {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovies().map {
                    it.asDomain()
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResults>>> {
                return remoteDataSource.getAllMovies()
            }

            override suspend fun saveCallResult(data: List<MovieResults>) {
                localDataSource.insertMovies(data.asMovieEntities())
            }

        }.asLiveData()
    }

    override fun getAllFavoriteMovies(): Flow<List<Movie>> {
        return localDataSource.getFavoriteMovies().map {
            it.asDomain()
        }


    }

    override fun getSelectedMovie(itemName: String): Flow<Resource<Movie>> {
        return object : com.rumahgugun.pilem.core.data.NetworkBoundResource<Movie, DetailMovieResponse>(appExecutors) {
            override fun loadFromDB(): Flow<Movie> {
                return localDataSource.getSelectedMovie(itemName).map {
                    it.asMovieDomain()
                }

            }

            override fun shouldFetch(data: Movie?): Boolean {
                return data == null
            }

            override suspend fun createCall(): Flow<ApiResponse<DetailMovieResponse>> {
                return remoteDataSource.getSelectedMovie(itemName)
            }

            override suspend fun saveCallResult(data: DetailMovieResponse) {
                val item = data.asMovieEntity()
                localDataSource.insertMovie(item)
            }

        }.asLiveData()
    }

    override fun setFavoriteItem(item: Movie, newState: Boolean) {

        appExecutors.diskIO().execute {
            localDataSource.updateFavoriteMovie(
                item.asMovieEntity(),
                newState
            )
        }
    }

    /*********************************************************************/

    override fun getAllTvs(): Flow<Resource<List<Tv>>> {
        return object : com.rumahgugun.pilem.core.data.NetworkBoundResource<List<Tv>, List<TvResults>>(appExecutors) {
            override fun loadFromDB(): Flow<List<Tv>> {
                return localDataSource.getAllTvs().map {
                    it.asTvDomain()
                }
            }

            override fun shouldFetch(data: List<Tv>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<TvResults>>> {
                return remoteDataSource.getAllTvs()
            }

            override suspend fun saveCallResult(data: List<TvResults>) {
                localDataSource.insertTvs(data.asTvEntities())
            }

        }.asLiveData()
    }

    override fun getAllFavoriteTvs(): Flow<List<Tv>> {
        return localDataSource.getFavoriteTvs().map {
            it.asTvDomain()
        }
    }

    override fun getSelectedTv(itemName: String): Flow<Resource<Tv>> {
        return object : com.rumahgugun.pilem.core.data.NetworkBoundResource<Tv, DetailTvResponse>(appExecutors) {
            override fun loadFromDB(): Flow<Tv> {
                return localDataSource.getSelectedTv(itemName).map {
                    it.asTvDomain()
                }
            }

            override fun shouldFetch(data: Tv?): Boolean {
                return data == null
            }

            override suspend fun createCall(): Flow<ApiResponse<DetailTvResponse>> {
                return remoteDataSource.getSelectedTv(itemName)
            }

            override suspend fun saveCallResult(data: DetailTvResponse) {
                val item = data.asTvEntity()
                localDataSource.insertTv(item)
            }

        }.asLiveData()
    }

    override fun getProfile(): Gugun {
        return localDataSource.getProfil().asDomain()
    }

    override fun setFavoriteItem(item: Tv, newState: Boolean) {
        appExecutors.diskIO().execute {
            localDataSource.updateFavoriteTv(
                item.asTvEntity(),
                newState
            )
        }
    }


    /*override fun getAllMovies(): LiveData<List<MovieEntity>> {
        val moviesResponse = MutableLiveData<List<MovieEntity>>()
        remoteDataSource.getAllMovies(object : RemoteDataSource.GetAllMoviesCallback {
            override fun onAllMoviesReceived(listMovieResponse: ListMovieResponse) {
                val list = ArrayList<MovieEntity>()
                listMovieResponse.itemMovies?.forEach { response ->
                    val item = MovieEntity(
                        response.id,
                        response.picture,
                        response.name.toString(),
                        response.description,
                        response.duration,
                        response.rating
                    )
                    list.add(item)
                }
                moviesResponse.postValue(list)
            }
        })
        return moviesResponse
    }

    override fun getAllTvs(): LiveData<List<TvEntity>> {
        val tvsResponse = MutableLiveData<List<TvEntity>>()
        remoteDataSource.getAllTvs(object : RemoteDataSource.GetAllTvCallback {
            override fun onAllTvReceived(listTvResponse: ListTvResponse) {
                val list = ArrayList<TvEntity>()
                listTvResponse.itemShows?.forEach { response ->
                    val item = TvEntity(
                        response.id,
                        response.picture,
                        response.name.toString(),
                        response.description,
                        response.duration,
                        response.rating
                    )
                    list.add(item)
                }
                tvsResponse.postValue(list)
            }
        })
        return tvsResponse
    }

    override fun getSelectedTv(itemName: String): LiveData<TvEntity> {
        val tvResponse = MutableLiveData<TvEntity>()
        remoteDataSource.getSelectedTv(
            itemName,
            object : RemoteDataSource.GetSelectedTvCallback {
                override fun onSelectedTvReceived(selectedTv: TvResponse?) {
                    val entity = TvEntity(
                        selectedTv?.id,
                        selectedTv?.picture,
                        selectedTv?.name.toString(),
                        selectedTv?.description,
                        selectedTv?.duration,
                        selectedTv?.rating
                    )
                    tvResponse.postValue(entity)
                }
            })
        return tvResponse
    }

    override fun getSelectedMovie(itemName: String): LiveData<MovieEntity> {
        val movieResponse = MutableLiveData<MovieEntity>()
        remoteDataSource.getSelectedMovie(
            itemName,
            object : RemoteDataSource.GetSelectedMovieCallback {
                override fun onSelectedMovieReceived(selectedMovie: MovieResponse?) {
                    val entity = MovieEntity(
                        selectedMovie?.id,
                        selectedMovie?.picture,
                        selectedMovie?.name.toString(),
                        selectedMovie?.description,
                        selectedMovie?.duration,
                        selectedMovie?.rating
                    )
                    movieResponse.postValue(entity)
                }

            })
        return movieResponse
    }*/

}