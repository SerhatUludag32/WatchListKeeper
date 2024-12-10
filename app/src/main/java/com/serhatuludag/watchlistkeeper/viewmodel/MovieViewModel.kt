package com.serhatuludag.watchlistkeeper.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.serhatuludag.watchlistkeeper.model.ImageResponse
import com.serhatuludag.watchlistkeeper.model.Movie
import com.serhatuludag.watchlistkeeper.repo.MovieRepositoryInterface
import com.serhatuludag.watchlistkeeper.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val repository: MovieRepositoryInterface) : ViewModel() {

    // Movie Fragment

    val artists = repository.getMovie()

    // ImageApi Fragment

    private val images = MutableLiveData<Resource<ImageResponse>>()
    val imageList : LiveData<Resource<ImageResponse>>
        get() = images

    private val selectedImage = MutableLiveData<String>()
    val selectedImageUrl : LiveData<String>
        get() = selectedImage

    // Movie Details Fragment

    private var insertMovieMsg = MutableLiveData<Resource<Movie>>()
    val insertMovieMessage : LiveData<Resource<Movie>>
        get() = insertMovieMsg

    fun resetInsertMovieMsg() {
        insertMovieMsg = MutableLiveData<Resource<Movie>>()

    }


    fun setSelectedImage(url : String) {
        selectedImage.postValue(url)
    }

    fun deleteMovie(movie: Movie) = viewModelScope.launch {
        repository.deleteMovie(movie)
    }

    fun insertMovie(movie: Movie) = viewModelScope.launch {
        repository.insertMovie(movie)
    }

    fun makeMovie(name : String, directorName : String, releaseYear : String){
        if (name.isEmpty() || directorName.isEmpty() || releaseYear.isEmpty()){
            insertMovieMsg.postValue(Resource.error("Enter name, director, year",null))
            return
        }
        val yearInt = try {
            releaseYear.toInt()
        } catch (e: Exception) {
            insertMovieMsg.postValue(Resource.error("Year should be number",null))
            return
        }
        val movie = Movie(name,directorName,yearInt,selectedImage.value ?: "")
        insertMovie(movie)
        setSelectedImage("")
        insertMovieMsg.postValue(Resource.success(movie))
    }

    fun searchForImage(searchString : String) {
        if (searchString.isEmpty()){
            return
        }

        images.value = Resource.loading(null)
        viewModelScope.launch {
            val response = repository.searchImage(searchString)
            images.value = response
        }

    }


}