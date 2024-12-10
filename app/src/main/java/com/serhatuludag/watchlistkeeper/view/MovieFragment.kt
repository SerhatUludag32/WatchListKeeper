package com.serhatuludag.watchlistkeeper.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.serhatuludag.watchlistkeeper.R
import com.serhatuludag.watchlistkeeper.databinding.FragmentMovieBinding

class MovieFragment : Fragment(R.layout.fragment_movie) {

    private var fragmentBinding : FragmentMovieBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentMovieBinding.bind(view)
        fragmentBinding = binding

        binding.fab.setOnClickListener {
            findNavController().navigate(MovieFragmentDirections.actionMovieFragmentToMovieDetailsFragment())
        }


    }

    override fun onDestroyView() {
        fragmentBinding = null
        super.onDestroyView()

    }
}