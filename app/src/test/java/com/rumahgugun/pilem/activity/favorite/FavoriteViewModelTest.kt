package com.rumahgugun.pilem.activity.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.rumahgugun.pilem.activity.main.MainViewModel
import com.rumahgugun.pilem.data.source.PilemRepository
import com.rumahgugun.pilem.data.source.local.entity.MovieEntity
import com.rumahgugun.pilem.data.source.local.entity.TvEntity
import com.rumahgugun.pilem.utils.DataDummy
import com.rumahgugun.pilem.vo.Resource
import junit.framework.Assert
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.Executors

@RunWith(MockitoJUnitRunner::class)
class FavoriteViewModelTest {

    private lateinit var viewModel: FavoriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var pilemRepository: PilemRepository

    @Mock
    private lateinit var observerMovies: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var observerTvs: Observer<PagedList<TvEntity>>

    @Before
    fun setUp() {
        viewModel = FavoriteViewModel(pilemRepository)
    }

    @Test
    fun `getMovies should be success`() {
        val expected = MutableLiveData<PagedList<MovieEntity>>()
        expected.value = PagedTestMovieDataSources.snapshot(DataDummy.generateDummyMovies())

        Mockito.`when`(pilemRepository.getAllFavoriteMovies()).thenReturn(expected)

        viewModel.getMovies().observeForever(observerMovies)
        Mockito.verify(observerMovies).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModel.getMovies().value
        Assert.assertEquals(expectedValue, actualValue)
        Assert.assertEquals(expectedValue?.snapshot(), actualValue?.snapshot())
        Assert.assertEquals(expectedValue?.size, actualValue?.size)
    }

    @Test
    fun `getMovies should be success but data is empty`() {
        val expected = MutableLiveData<PagedList<MovieEntity>>()
        expected.value = PagedTestMovieDataSources.snapshot()

        Mockito.`when`(pilemRepository.getAllFavoriteMovies()).thenReturn(expected)

        viewModel.getMovies().observeForever(observerMovies)
        Mockito.verify(observerMovies).onChanged(expected.value)

        val actualValueDataSize = viewModel.getMovies().value?.size
        org.junit.Assert.assertTrue(
            "size of data should be 0, actual is $actualValueDataSize",
            actualValueDataSize == 0
        )
    }

    @Test
    fun `getTvs should be success`() {
        val expected = MutableLiveData<PagedList<TvEntity>>()
        expected.value = PagedTestTvDataSources.snapshot(DataDummy.generateDummyTvs())

        Mockito.`when`(pilemRepository.getAllFavoriteTvs()).thenReturn(expected)

        viewModel.getTvs().observeForever(observerTvs)
        Mockito.verify(observerTvs).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModel.getTvs().value
        Assert.assertEquals(expectedValue, actualValue)
        Assert.assertEquals(expectedValue?.snapshot(), actualValue?.snapshot())
        Assert.assertEquals(expectedValue?.size, actualValue?.size)
    }

    @Test
    fun `getTvs should be success but data is empty`() {
        val expected = MutableLiveData<PagedList<TvEntity>>()
        expected.value = PagedTestTvDataSources.snapshot()

        Mockito.`when`(pilemRepository.getAllFavoriteTvs()).thenReturn(expected)

        viewModel.getTvs().observeForever(observerTvs)
        Mockito.verify(observerTvs).onChanged(expected.value)

        val actualValueDataSize = viewModel.getTvs().value?.size
        org.junit.Assert.assertTrue(
            "size of data should be 0, actual is $actualValueDataSize",
            actualValueDataSize == 0
        )
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