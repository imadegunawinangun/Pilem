package com.rumahgugun.pilem.core.data.source.remote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvResponse(
    var id: String,
    var picture: String,
    var name: String,
    var description: String,
    var duration: String,
    var rating: String
) : Parcelable