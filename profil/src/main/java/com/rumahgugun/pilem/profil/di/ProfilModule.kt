package com.rumahgugun.pilem.profil.di

import com.rumahgugun.pilem.profil.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val profilModule = module {
    viewModel { ProfileViewModel(get()) }
}