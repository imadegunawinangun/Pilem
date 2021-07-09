package com.rumahgugun.pilem.activity.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.rumahgugun.pilem.data.source.PilemRepository
import com.rumahgugun.pilem.data.source.local.entity.MovieEntity
import com.rumahgugun.pilem.data.source.local.entity.TvEntity
import com.rumahgugun.pilem.utils.DataDummy
import com.rumahgugun.pilem.vo.Resource
import junit.framework.Assert.assertEquals
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.Executors

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    private lateinit var viewModel: MainViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var pilemRepository: PilemRepository

    @Mock
    private lateinit var observerMovies: Observer<Resource<PagedList<MovieEntity>>>

    @Mock
    private lateinit var observerTvs: Observer<Resource<PagedList<TvEntity>>>

    @Before
    fun setUp() {
        viewModel = MainViewModel(pilemRepository)
    }

    @Test
    fun `getMovies should be success`() {
        val movies = PagedTestMovieDataSources.snapshot(DataDummy.generateDummyMovies())
        val expected = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        expected.value = Resource.success(movies)

        Mockito.`when`(pilemRepository.getAllMovies()).thenReturn(expected)

        viewModel.getMovies().observeForever(observerMovies)
        Mockito.verify(observerMovies).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModel.getMovies().value
        assertEquals(expectedValue, actualValue)
        assertEquals(expectedValue?.data, actualValue?.data)
        assertEquals(expectedValue?.data?.size, actualValue?.data?.size)
    }

    @Test
    fun `getMovies should be success but data is empty`() {
        val courses = PagedTestMovieDataSources.snapshot()
        val expected = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        expected.value = Resource.success(courses)

        Mockito.`when`(pilemRepository.getAllMovies()).thenReturn(expected)

        viewModel.getMovies().observeForever(observerMovies)
        Mockito.verify(observerMovies).onChanged(expected.value)

        val actualValueDataSize = viewModel.getMovies().value?.data?.size
        Assert.assertTrue(
            "size of data should be 0, actual is $actualValueDataSize",
            actualValueDataSize == 0
        )
    }

    @Test
    fun `getMovies should be error`() {
        val expectedMessage = "Something happen dude!"
        val expected = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        expected.value = Resource.error(expectedMessage, null)

        Mockito.`when`(pilemRepository.getAllMovies()).thenReturn(expected)

        viewModel.getMovies().observeForever(observerMovies)
        Mockito.verify(observerMovies).onChanged(expected.value)

        val actualMessage = viewModel.getMovies().value?.message
        Assert.assertEquals(expectedMessage, actualMessage)
    }

    @Test
    fun `getTvs should be success`() {
        val movies = PagedTestTvDataSources.snapshot(DataDummy.generateDummyTvs())
        val expected = MutableLiveData<Resource<PagedList<TvEntity>>>()
        expected.value = Resource.success(movies)

        Mockito.`when`(pilemRepository.getAllTvs()).thenReturn(expected)

        viewModel.getTvs().observeForever(observerTvs)
        Mockito.verify(observerTvs).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModel.getTvs().value
        assertEquals(expectedValue, actualValue)
        assertEquals(expectedValue?.data, actualValue?.data)
        assertEquals(expectedValue?.data?.size, actualValue?.data?.size)
    }

    @Test
    fun `getTvs should be success but data is empty`() {
        val courses = PagedTestTvDataSources.snapshot()
        val expected = MutableLiveData<Resource<PagedList<TvEntity>>>()
        expected.value = Resource.success(courses)

        Mockito.`when`(pilemRepository.getAllTvs()).thenReturn(expected)

        viewModel.getTvs().observeForever(observerTvs)
        Mockito.verify(observerTvs).onChanged(expected.value)

        val actualValueDataSize = viewModel.getTvs().value?.data?.size
        Assert.assertTrue(
            "size of data should be 0, actual is $actualValueDataSize",
            actualValueDataSize == 0
        )
    }

    @Test
    fun `getTvs should be error`() {
        val expectedMessage = "Something happen dude!"
        val expected = MutableLiveData<Resource<PagedList<TvEntity>>>()
        expected.value = Resource.error(expectedMessage, null)

        Mockito.`when`(pilemRepository.getAllTvs()).thenReturn(expected)

        viewModel.getTvs().observeForever(observerTvs)
        Mockito.verify(observerTvs).onChanged(expected.value)

        val actualMessage = viewModel.getTvs().value?.message
        Assert.assertEquals(expectedMessage, actualMessage)
    }

    class PagedTestMovieDataSources private constructor(private val items: List<MovieEntity>) : PositionalDataSource<MovieEntity>() {
        companion object {
            fun snapshot(items: List<MovieEntity> = listOf()): PagedList<MovieEntity> {
                return PagedList.Builder(PagedTestMovieDataSources(items), 10)
                    .setNotifyExecutor(Executors.newSingleThreadExecutor())
                    .setFetchExecutor(Executors.newSingleThreadExecutor())
                    .build()
            }
        }

        override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<MovieEntity>) {
            callback.onResult(items, 0, items.size)
        }

        override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<MovieEntity>) {
            val start = params.startPosition
            val end = params.startPosition + params.loadSize
            callback.onResult(items.subList(start, end))
        }
    }
    class PagedTestTvDataSources private constructor(private val items: List<TvEntity>) : PositionalDataSource<TvEntity>() {
        companion object {
            fun snapshot(items: List<TvEntity> = listOf()): PagedList<TvEntity> {
                return PagedList.Builder(PagedTestTvDataSources(items), 10)
                    .setNotifyExecutor(Executors.newSingleThreadExecutor())
                    .setFetchExecutor(Executors.newSingleThreadExecutor())
                    .build()
            }
        }

        override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<TvEntity>) {
            callback.onResult(items, 0, items.size)
        }

        override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<TvEntity>) {
            val start = params.startPosition
            val end = params.startPosition + params.loadSize
            callback.onResult(items.subList(start, end))
        }
    }
}