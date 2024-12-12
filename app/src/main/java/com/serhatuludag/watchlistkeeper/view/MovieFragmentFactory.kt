package com.serhatuludag.watchlistkeeper.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import com.serhatuludag.watchlistkeeper.adapter.ImageRecyclerAdapter
import com.serhatuludag.watchlistkeeper.adapter.MovieRecyclerAdapter
import com.serhatuludag.watchlistkeeper.viewmodel.MovieViewModel
import javax.inject.Inject

class MovieFragmentFactory @Inject constructor(
    private val glide : RequestManager,
    private val movieRecyclerAdapter: MovieRecyclerAdapter,
    private val imageRecyclerAdapter: ImageRecyclerAdapter,
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {

        return when(className) {
            MovieFragment :: class.java.name -> MovieFragment(movieRecyclerAdapter)
            MovieDetailsFragment :: class.java.name -> MovieDetailsFragment(glide)
            ImageApiFragment :: class.java.name -> ImageApiFragment(imageRecyclerAdapter)
            else -> super.instantiate(classLoader, className)
        }


        return super.instantiate(classLoader, className)
    }
}