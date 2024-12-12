package com.serhatuludag.watchlistkeeper.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.serhatuludag.watchlistkeeper.R
import com.serhatuludag.watchlistkeeper.databinding.FragmentMovieDetailsBinding
import com.serhatuludag.watchlistkeeper.util.Status
import com.serhatuludag.watchlistkeeper.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MovieDetailsFragment @Inject constructor(val glide : RequestManager): Fragment(R.layout.fragment_movie_details) {

    private var fragmentBinding: FragmentMovieDetailsBinding? = null
    val viewModel: MovieViewModel by activityViewModels()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val binding = FragmentMovieDetailsBinding.bind(view)
        fragmentBinding = binding

        subscribeToObservers()

        binding.movieImageView.setOnClickListener {
            findNavController().navigate(
                MovieDetailsFragmentDirections.actionMovieDetailsFragmentToImageApiFragment()
            )
        }


        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                viewModel.setSelectedImage("")
                findNavController().popBackStack()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(callback)

        binding.saveButton.setOnClickListener {
            viewModel.makeMovie(
                binding.movieNameText.text.toString(),
                binding.directorNameText.text.toString(),
                binding.ReleaseYearText.text.toString()
            )
        }

    }

    private fun subscribeToObservers() {
        viewModel.selectedImageUrl.observe(viewLifecycleOwner) { url ->
            fragmentBinding?.let { binding ->
                glide.load(url).into(binding.movieImageView)
            }
        }

        viewModel.insertMovieMessage.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    Toast.makeText(requireActivity(), "Success", Toast.LENGTH_SHORT).show()
                    findNavController().popBackStack()
                    viewModel.resetInsertMovieMsg()
                }

                Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message ?: "Error", Toast.LENGTH_SHORT)
                        .show()
                }

                Status.LOADING -> {
                    // do nothing

                }
            }
        }
    }


    override fun onDestroyView() {
        fragmentBinding = null
        super.onDestroyView()
    }
}