package com.rumahgugun.pilem.utils

import android.content.Context
import com.google.gson.Gson
import com.rumahgugun.pilem.data.source.remote.response.ListMovieResponse
import com.rumahgugun.pilem.data.source.remote.response.ListTvResponse
import com.rumahgugun.pilem.data.source.remote.response.MovieResponse
import com.rumahgugun.pilem.data.source.remote.response.TvResponse
import java.io.IOException
import java.io.InputStream

class JsonHelper(private val context: Context) {
    private val fileTv = "tv.json"
    private val fileMovie = "movies.json"

    private fun parsingFileToString(fileName: String): String? {
        val jsonString: String?
        var inputStream: InputStream? = null
        try {
            inputStream = context.assets?.open(fileName)

            val size = inputStream?.available()
            val buffer = size?.let { ByteArray(it) }

            inputStream?.read(buffer)

            jsonString = buffer?.let { String(it) }
            return jsonString
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            inputStream?.close()

        }
        return null
    }

    fun getMovieJson(): ListMovieResponse {
        val string = parsingFileToString(fileMovie)
        return Gson().fromJson(string, ListMovieResponse::class.java)
    }

    fun getTvJson(): ListTvResponse {
        val string = parsingFileToString(fileTv)
        return Gson().fromJson(string, ListTvResponse::class.java)
    }

    fun getSelectedTv(itemName: String): TvResponse {
        var tvResponse: TvResponse? = null
        getTvJson().itemShows.forEach { entity ->
            if (entity.name == itemName) {
                tvResponse = TvResponse(
                    entity.id,
                    entity.picture,
                    entity.name,
                    entity.description,
                    entity.duration,
                    entity.rating
                )
            }
        }
        return tvResponse!!
    }
    fun getSelectedMovie(itemName: String): MovieResponse {
        var movieResponse:MovieResponse? = null
        getMovieJson().itemMovies.forEach { entity ->
            if (entity.name == itemName) {
                val response = MovieResponse(
                    entity.id,
                    entity.picture,
                    entity.name,
                    entity.description,
                    entity.duration,
                    entity.rating
                )
                movieResponse = response
            }
        }
        return movieResponse!!
    }
}
