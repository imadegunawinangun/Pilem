package com.rumahgugun.pilem.profil

import androidx.lifecycle.ViewModel
import com.rumahgugun.pilem.core.domain.usecase.PilemUseCase

class ProfileViewModel(pilemUseCase: PilemUseCase):ViewModel() {
    val profil = pilemUseCase.getProfile()
}