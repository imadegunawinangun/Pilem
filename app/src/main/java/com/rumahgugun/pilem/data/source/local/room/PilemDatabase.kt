package com.rumahgugun.pilem.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rumahgugun.pilem.data.source.local.entity.MovieEntity
import com.rumahgugun.pilem.data.source.local.entity.TvEntity

@Database(entities = [MovieEntity::class, TvEntity::class], version = 1, exportSchema = false)
abstract class PilemDatabase : RoomDatabase() {
    abstract fun pilemDao(): PilemDao

    companion object {

        @Volatile
        private var INSTANCE: PilemDatabase? = null

        fun getInstance(context: Context): PilemDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    PilemDatabase::class.java,
                    "Pillem.db"
                ).build().apply {
                    INSTANCE = this
                }
            }
    }
}