package com.rumahgugun.pilem.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import org.mockito.Mockito.mock
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.doAnswer
import com.rumahgugun.pilem.data.source.local.LocalDataSource
import com.rumahgugun.pilem.data.source.local.entity.MovieEntity
import com.rumahgugun.pilem.data.source.local.entity.TvEntity
import com.rumahgugun.pilem.utils.LiveDataTestUtil
import com.rumahgugun.pilem.data.source.remote.RemoteDataSource
import com.rumahgugun.pilem.data.source.remote.response.ListMovieResponse
import com.rumahgugun.pilem.data.source.remote.response.ListTvResponse
import com.rumahgugun.pilem.utils.AppExecutors
import com.rumahgugun.pilem.utils.DataDummy
import com.rumahgugun.pilem.utils.PagedListUtil
import com.rumahgugun.pilem.vo.Resource
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito


class PilemRepositoryTest {
    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)

    private val pilemRepository = FakePilemRepository(remote, local, appExecutors)

    private val movieResponses =  DataDummy.generateRemoteDummyMovies()
    private val movieEntitiesParent = ListMovieResponse(itemMovies = movieResponses)
    private val tvResponses =  DataDummy.generateRemoteDummyTvs()
    private val tvEntitiesParent = ListTvResponse(itemShows = tvResponses)
    private val selectedMovieName = movieResponses[0].name
    private val selectedTvName = tvResponses[0].name

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun getAllMovies() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        Mockito.`when`(local.getAllMovies()).thenReturn(dataSourceFactory)
        pilemRepository.getAllMovies()

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovies()))
        verify(local).getAllMovies()
        Assert.assertNotNull(movieEntities.data)
        assertEquals(movieResponses.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getAllTvs() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvEntity>
        Mockito.`when`(local.getAllTvs()).thenReturn(dataSourceFactory)
        pilemRepository.getAllTvs()

        val tvEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTvs()))
        verify(local).getAllTvs()
        Assert.assertNotNull(tvEntities.data)
        assertEquals(tvResponses.size.toLong(), tvEntities.data?.size?.toLong())
    }

    @Test
    fun getSelectedTv() {
        val dummyEntity = MutableLiveData<TvEntity>()
        dummyEntity.value = DataDummy.generateDummyTvs()[0]
        Mockito.`when`<LiveData<TvEntity>>(local.getSelectedTv(selectedTvName)).thenReturn(dummyEntity)

        val courseEntities = LiveDataTestUtil.getValue(pilemRepository.getSelectedTv(selectedTvName))
        verify(local).getSelectedTv(selectedTvName)
        Assert.assertNotNull(courseEntities.data)
        Assert.assertNotNull(courseEntities.data?.name)
        assertEquals(tvResponses[0].name, courseEntities.data?.name)
    }

    @Test
    fun getSelectedMovie() {
        val dummyEntity = MutableLiveData<MovieEntity>()
        dummyEntity.value = DataDummy.generateDummyMovies()[0]
        Mockito.`when`<LiveData<MovieEntity>>(local.getSelectedMovie(selectedMovieName)).thenReturn(dummyEntity)

        val courseEntities = LiveDataTestUtil.getValue(pilemRepository.getSelectedMovie(selectedMovieName))
        verify(local).getSelectedMovie(selectedMovieName)
        Assert.assertNotNull(courseEntities.data)
        Assert.assertNotNull(courseEntities.data?.name)
        assertEquals(movieResponses[0].name, courseEntities.data?.name)
    }

    @Test
    fun getAllFavoriteMovies(){
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        Mockito.`when`(local.getFavoriteMovies()).thenReturn(dataSourceFactory)
        pilemRepository.getAllFavoriteMovies()

        val courseEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovies()))
        verify(local).getFavoriteMovies()
        Assert.assertNotNull(courseEntities)
        assertEquals(movieResponses.size.toLong(), courseEntities.data?.size?.toLong())
    }


    @Test
    fun getAllFavoriteTvs(){
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvEntity>
        Mockito.`when`(local.getFavoriteTvs()).thenReturn(dataSourceFactory)
        pilemRepository.getAllFavoriteTvs()

        val courseEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTvs()))
        verify(local).getFavoriteTvs()
        Assert.assertNotNull(courseEntities)
        assertEquals(tvResponses.size.toLong(), courseEntities.data?.size?.toLong())
    }

}