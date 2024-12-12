package com.serhatuludag.watchlistkeeper.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.serhatuludag.watchlistkeeper.R
import com.serhatuludag.watchlistkeeper.adapter.MovieRecyclerAdapter
import com.serhatuludag.watchlistkeeper.databinding.FragmentMovieBinding
import com.serhatuludag.watchlistkeeper.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MovieFragment @Inject constructor(val movieRecyclerAdapter: MovieRecyclerAdapter) : Fragment(R.layout.fragment_movie) {

    private var fragmentBinding : FragmentMovieBinding? = null
    private val viewModel: MovieViewModel by viewModels()

    private val swipeCallBack = object : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean = true

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val layoutPosition = viewHolder.layoutPosition
            val selectedMovie = movieRecyclerAdapter.movies[layoutPosition]
            viewModel.deleteMovie(selectedMovie)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //viewModel = ViewModelProvider(requireActivity()).get(MovieViewModel::class.java)

        val binding = FragmentMovieBinding.bind(view)
        fragmentBinding = binding

        subscribeToObservers()

        binding.recyclerViewMovie.apply {
            adapter = movieRecyclerAdapter
            layoutManager = LinearLayoutManager(requireContext())
            ItemTouchHelper(swipeCallBack).attachToRecyclerView(this)
        }

        ItemTouchHelper(swipeCallBack).attachToRecyclerView(binding.recyclerViewMovie)

        binding.fab.setOnClickListener {
            findNavController().navigate(MovieFragmentDirections.actionMovieFragmentToMovieDetailsFragment())
        }

    }

    private fun subscribeToObservers() {
        viewModel.movieList.observe(viewLifecycleOwner, {
            movieRecyclerAdapter.movies = it
        })

    }

    override fun onDestroyView() {
        fragmentBinding = null
        super.onDestroyView()

    }
}