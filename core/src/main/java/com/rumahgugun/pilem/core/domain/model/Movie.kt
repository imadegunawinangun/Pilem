package com.rumahgugun.pilem.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    var id: Int,

    var picture: String?,

    var name: String,

    var description: String?,

    var duration: String?,

    var rating: Double,

    var isFavorite: Boolean = false
) : Parcelable