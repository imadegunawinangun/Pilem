package com.rumahgugun.pilem.data.source

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.rumahgugun.pilem.data.source.local.LocalDataSource
import com.rumahgugun.pilem.data.source.local.entity.MovieEntity
import com.rumahgugun.pilem.data.source.local.entity.TvEntity
import com.rumahgugun.pilem.data.source.remote.ApiResponse
import com.rumahgugun.pilem.data.source.remote.RemoteDataSource
import com.rumahgugun.pilem.data.source.remote.response.MovieResponse
import com.rumahgugun.pilem.data.source.remote.response.TvResponse
import com.rumahgugun.pilem.utils.AppExecutors
import com.rumahgugun.pilem.vo.Resource

class PilemRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : PilemDataSource {

    companion object {
        @Volatile
        private var instance: PilemRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localDataSource: LocalDataSource,
            appExecutors: AppExecutors
        ): PilemRepository =
            instance ?: synchronized(this) {
                PilemRepository(remoteData, localDataSource, appExecutors).apply { instance = this }
            }
    }

    override fun getAllMovies(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object :
            NetworkBoundResource<PagedList<MovieEntity>, List<MovieResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> {
                return remoteDataSource.getAllMovies()
            }

            override fun saveCallResult(data: List<MovieResponse>) {
                val itemList = ArrayList<MovieEntity>()
                for (response in data) {
                    val item = MovieEntity(
                        response.id,
                        response.picture,
                        response.name,
                        response.description,
                        response.duration,
                        response.rating,
                        false
                    )
                    itemList.add(item)
                }
                localDataSource.insertMovies(itemList)
            }

        }.asLiveData()
    }

    override fun getAllFavoriteMovies(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteMovies(), config).build()
    }

    override fun getSelectedMovie(itemName: String): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, MovieResponse>(appExecutors){
            override fun loadFromDB(): LiveData<MovieEntity> {
                return localDataSource.getSelectedMovie(itemName)

            }

            override fun shouldFetch(data: MovieEntity?): Boolean {
                return data == null
            }

            override fun createCall(): LiveData<ApiResponse<MovieResponse>> {
                return remoteDataSource.getSelectedMovie(itemName)
            }

            override fun saveCallResult(data: MovieResponse) {
                    val item = MovieEntity(
                        data.id,
                        data.picture,
                        data.name,
                        data.description,
                        data.duration,
                        data.rating,
                        false
                    )
                localDataSource.insertMovie(item)
            }

        }.asLiveData()
    }

    override fun setFavoriteItem(item: MovieEntity, newState: Boolean) {
        appExecutors.diskIO().execute{ localDataSource.updateFavoriteMovie(item, newState)}
    }

    override fun getAllTvs(): LiveData<Resource<PagedList<TvEntity>>> {
        return object : NetworkBoundResource<PagedList<TvEntity>, List<TvResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<TvEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllTvs(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override fun createCall(): LiveData<ApiResponse<List<TvResponse>>> {
                return remoteDataSource.getAllTvs()
            }

            override fun saveCallResult(data: List<TvResponse>) {
                val itemList = ArrayList<TvEntity>()
                for (response in data) {
                    val item = TvEntity(
                        response.id,
                        response.picture,
                        response.name,
                        response.description,
                        response.duration,
                        response.rating,
                        false
                    )
                    itemList.add(item)
                }
                localDataSource.insertTvs(itemList)
            }

        }.asLiveData()
    }

    override fun getAllFavoriteTvs(): LiveData<PagedList<TvEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteTvs(), config).build()    }

    override fun getSelectedTv(itemName: String): LiveData<Resource<TvEntity>> {
        return object : NetworkBoundResource<TvEntity, TvResponse>(appExecutors){
            override fun loadFromDB(): LiveData<TvEntity> {
                return localDataSource.getSelectedTv(itemName)

            }

            override fun shouldFetch(data: TvEntity?): Boolean {
                return data == null
            }

            override fun createCall(): LiveData<ApiResponse<TvResponse>> {
                return remoteDataSource.getSelectedTv(itemName)
            }

            override fun saveCallResult(data: TvResponse) {
                val item = TvEntity(
                    data.id,
                    data.picture,
                    data.name,
                    data.description,
                    data.duration,
                    data.rating,
                    false
                )
                localDataSource.insertTv(item)
            }

        }.asLiveData()
    }

    override fun setFavoriteItem(item: TvEntity, newState: Boolean) {
        appExecutors.diskIO().execute{ localDataSource.updateFavoriteTv(item, newState)}
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