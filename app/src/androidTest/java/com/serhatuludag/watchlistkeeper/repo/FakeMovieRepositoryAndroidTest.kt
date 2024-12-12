package com.serhatuludag.watchlistkeeper.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.serhatuludag.watchlistkeeper.model.ImageResponse
import com.serhatuludag.watchlistkeeper.model.Movie
import com.serhatuludag.watchlistkeeper.util.Resource

class FakeMovieRepositoryAndroidTest : MovieRepositoryInterface {
    private val movies = mutableListOf<Movie>()
    private val movieLiveData = MutableLiveData<List<Movie>>(movies)

    override suspend fun insertMovie(movie: Movie) {
        movies.add(movie)
        refreshLiveData()

    }

    override suspend fun deleteMovie(movie: Movie) {
        movies.remove(movie)
        refreshLiveData()
    }

    override fun getMovie(): LiveData<List<Movie>> {
        return movieLiveData
    }

    override suspend fun searchImage(imageString: String): Resource<ImageResponse> {
        return Resource.success(ImageResponse(listOf(),0,0))
    }

    private fun refreshLiveData() {
        movieLiveData.postValue(movies)
    }
}