package com.rumahgugun.pilem.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rumahgugun.pilem.activity.detail.movie.DetailMovieViewModel
import com.rumahgugun.pilem.activity.detail.tv.DetailTvViewModel
import com.rumahgugun.pilem.activity.favorite.FavoriteViewModel
import com.rumahgugun.pilem.activity.main.MainViewModel
import com.rumahgugun.pilem.data.source.PilemRepository
import com.rumahgugun.pilem.di.Injection

class ViewModelFactory private constructor(private val mPilemRepository: PilemRepository) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                ViewModelFactory(Injection.provideRepository(context)).apply {
                    instance = this
                }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(mPilemRepository) as T
            }
            modelClass.isAssignableFrom(DetailMovieViewModel::class.java) -> {
                DetailMovieViewModel(mPilemRepository) as T
            }
            modelClass.isAssignableFrom(DetailTvViewModel::class.java) -> {
                DetailTvViewModel(mPilemRepository) as T
            }
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(mPilemRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}