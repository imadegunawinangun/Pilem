package com.rumahgugun.pilem.core.domain.model

import android.os.Parcelable
import com.rumahgugun.pilem.core.data.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.parcelize.Parcelize

@Parcelize
data class Gugun(
    val nama: String ,
    val status: String,
    val email: String
) : Parcelable
