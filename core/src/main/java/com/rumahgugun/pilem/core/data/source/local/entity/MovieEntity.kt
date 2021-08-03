package com.rumahgugun.pilem.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_favorite_movie")
data class MovieEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "picture")
    var picture: String?,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "description")
    var description: String?,

    @ColumnInfo(name="duration")
    var duration: String?,

    @ColumnInfo(name = "rating")
    var rating: Double,

    @ColumnInfo(name="isFavorite")
    var isFavorite: Boolean = false
)