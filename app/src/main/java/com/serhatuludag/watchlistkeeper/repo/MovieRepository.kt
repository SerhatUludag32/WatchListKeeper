package com.serhatuludag.watchlistkeeper.repo

import androidx.lifecycle.LiveData
import com.serhatuludag.watchlistkeeper.model.ImageResponse
import com.serhatuludag.watchlistkeeper.model.Movie
import com.serhatuludag.watchlistkeeper.roomdb.MovieDao
import com.serhatuludag.watchlistkeeper.service.RetrofitAPI
import com.serhatuludag.watchlistkeeper.util.Resource
import javax.inject.Inject

class MovieRepository @Inject constructor(private val movieDao: MovieDao,
                                          private val retrofitAPI: RetrofitAPI
): MovieRepositoryInterface {

    override suspend fun insertMovie(movie: Movie) {
        movieDao.insertMovie(movie)
    }

    override suspend fun deleteMovie(movie: Movie) {
        movieDao.deleteMovie(movie)
    }

    override fun getMovie(): LiveData<List<Movie>> {
        return movieDao.observeMovies()
    }

    override suspend fun searchImage(imageString: String): Resource<ImageResponse> {
        return try {
            val response = retrofitAPI.imageSearch(imageString)

            if (response.isSuccessful){
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("Error",null)
            } else{
                Resource.error("Error",null)
            }

        } catch (e: Exception) {
            Resource.error("No data!", null)
        }
    }
}