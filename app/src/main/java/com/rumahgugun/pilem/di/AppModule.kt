package com.rumahgugun.pilem.di
import com.rumahgugun.pilem.core.domain.usecase.PilemInteractor
import com.rumahgugun.pilem.core.domain.usecase.PilemUseCase
import com.rumahgugun.pilem.detail.movie.DetailMovieViewModel
import com.rumahgugun.pilem.detail.tv.DetailTvViewModel
import com.rumahgugun.pilem.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<PilemUseCase> { PilemInteractor(get()) }
}

val viewModelModule = module {
    viewModel { DetailMovieViewModel(get()) }
    viewModel { DetailTvViewModel(get()) }
    viewModel { MainViewModel(get()) }

}