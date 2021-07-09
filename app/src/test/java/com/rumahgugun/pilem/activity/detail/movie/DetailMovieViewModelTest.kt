package com.rumahgugun.pilem.activity.detail.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.rumahgugun.pilem.data.source.PilemRepository
import com.rumahgugun.pilem.data.source.local.entity.MovieEntity
import com.rumahgugun.pilem.utils.DataDummy
import com.rumahgugun.pilem.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailMovieViewModelTest {
    private lateinit var viewModel: DetailMovieViewModel
    private val dummyMovies = DataDummy.generateDummyMovies()[0]
    private val nameItemMovie = dummyMovies.name

    @Mock
    private lateinit var pilemRepository: PilemRepository

    @Mock
    private lateinit var observer: Observer<Resource<MovieEntity>>

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel(pilemRepository)
        viewModel.setSelectedItem(nameItemMovie)
    }

    @Test
    fun `setSelectedMovie should be success`() {
        val expected = MutableLiveData<Resource<MovieEntity>>()
        expected.value = Resource.success(DataDummy.generateDummyMovies()[0])

        `when`(pilemRepository.getSelectedMovie(nameItemMovie)).thenReturn(expected)

        viewModel.getMovieItem.observeForever(observer)

        verify(observer).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModel.getMovieItem.value

        assertEquals(expectedValue, actualValue)
    }
    @Test
    fun `setBookmark should be success trigger getMovieItem observer`() {
        val expected = MutableLiveData<Resource<MovieEntity>>()
        expected.value = Resource.success(DataDummy.generateDummyMovies()[0])

        `when`(pilemRepository.getSelectedMovie(nameItemMovie)).thenReturn(expected)

        viewModel.setBookmark()
        viewModel.getMovieItem.observeForever(observer)

        verify(observer).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModel.getMovieItem.value

        assertEquals(expectedValue, actualValue)
    }


}
