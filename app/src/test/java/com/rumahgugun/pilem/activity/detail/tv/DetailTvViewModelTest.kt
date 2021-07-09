package com.rumahgugun.pilem.activity.detail.tv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.rumahgugun.pilem.data.source.PilemRepository
import com.rumahgugun.pilem.data.source.local.entity.MovieEntity
import com.rumahgugun.pilem.data.source.local.entity.TvEntity
import com.rumahgugun.pilem.utils.DataDummy
import com.rumahgugun.pilem.vo.Resource
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailTvViewModelTest {

    private lateinit var viewModel: DetailTvViewModel
    private val dummyTvs = DataDummy.generateDummyTvs()[0]
    private val nameItemTv = dummyTvs.name

    @Mock
    private lateinit var pilemRepository: PilemRepository

    @Mock
    private lateinit var observer: Observer<Resource<TvEntity>>

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = DetailTvViewModel(pilemRepository)
        viewModel.setSelectedItem(nameItemTv)
    }

    @Test
    fun `setSelectedTv should be success`() {
        val expected = MutableLiveData<Resource<TvEntity>>()
        expected.value = Resource.success(DataDummy.generateDummyTvs()[0])

        Mockito.`when`(pilemRepository.getSelectedTv(nameItemTv)).thenReturn(expected)

        viewModel.getTvItem.observeForever(observer)

        Mockito.verify(observer).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModel.getTvItem.value

        assertEquals(expectedValue, actualValue)
    }

    @Test
    fun `setBookmark should be success trigger getTvItem observer`() {
        val expected = MutableLiveData<Resource<TvEntity>>()
        expected.value = Resource.success(DataDummy.generateDummyTvs()[0])

        Mockito.`when`(pilemRepository.getSelectedTv(nameItemTv)).thenReturn(expected)

        viewModel.setBookmark()
        viewModel.getTvItem.observeForever(observer)

        Mockito.verify(observer).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModel.getTvItem.value

        assertEquals(expectedValue, actualValue)
    }
}