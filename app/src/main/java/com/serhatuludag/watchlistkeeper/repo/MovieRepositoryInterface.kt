package com.serhatuludag.watchlistkeeper.repo

import androidx.lifecycle.LiveData
import com.serhatuludag.watchlistkeeper.model.ImageResponse
import com.serhatuludag.watchlistkeeper.model.Movie
import com.serhatuludag.watchlistkeeper.util.Resource

interface MovieRepositoryInterface {

    suspend fun insertMovie(movie : Movie)

    suspend fun deleteMovie(movie : Movie)

    fun getMovie() : LiveData<List<Movie>>

    suspend fun searchImage(imageString : String) : Resource<ImageResponse>
}