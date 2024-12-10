package com.serhatuludag.watchlistkeeper.view

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.serhatuludag.watchlistkeeper.R
import com.serhatuludag.watchlistkeeper.databinding.FragmentMovieDetailsBinding
import java.util.Objects
import javax.inject.Inject

class MovieDetailsFragment @Inject constructor(glide : RequestManager): Fragment(R.layout.fragment_movie_details) {

    private var fragmentBinding : FragmentMovieDetailsBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentMovieDetailsBinding.bind(view)
        fragmentBinding = binding

        binding.movieImageView.setOnClickListener {
            findNavController().navigate(MovieDetailsFragmentDirections.actionMovieDetailsFragmentToImageApiFragment())
        }

        val callback = object : OnBackPressedCallback(true) {

            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(callback)

    }


    override fun onDestroyView() {
        fragmentBinding = null
        super.onDestroyView()


    }
}