package com.serhatuludag.watchlistkeeper.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.serhatuludag.watchlistkeeper.R
import com.serhatuludag.watchlistkeeper.adapter.ImageRecyclerAdapter
import com.serhatuludag.watchlistkeeper.databinding.FragmentImageApiBinding
import com.serhatuludag.watchlistkeeper.util.Status
import com.serhatuludag.watchlistkeeper.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class ImageApiFragment @Inject constructor(val imageRecyclerAdapter: ImageRecyclerAdapter) : Fragment(R.layout.fragment_image_api) {

    private var fragmentBinding: FragmentImageApiBinding? = null
    private val viewModel: MovieViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentImageApiBinding.bind(view)
        fragmentBinding = binding

        var job: Job? = null

        binding.searchText.addTextChangedListener {
            job?.cancel()
            job = lifecycleScope.launch {
                delay(1000)
                it?.let {
                    if (it.toString().isNotEmpty()) {
                        viewModel.searchForImage(it.toString())
                    }
                }
            }
        }

        subscribeToObservers()


        binding.imageRecyclerView.adapter = imageRecyclerAdapter
        binding.imageRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        imageRecyclerAdapter.setOnItemClickListener {
            findNavController().popBackStack()
            viewModel.setSelectedImage(it)
        }
    }

    private fun subscribeToObservers() {
        viewModel.imageList.observe(viewLifecycleOwner) {
            when(it.status){
                Status.SUCCESS -> {
                    val urls = it.data?.hits?.map { imageResult -> imageResult.previewURL }
                    imageRecyclerAdapter.images = urls ?: listOf()
                    fragmentBinding?.progressBar?.visibility = View.GONE
                }
                Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message ?: "An unknown error occurred", Toast.LENGTH_LONG).show()
                    fragmentBinding?.progressBar?.visibility = View.GONE
                    // show error message
                }
                Status.LOADING -> {
                    fragmentBinding?.progressBar?.visibility = View.VISIBLE
                    // show loading
                }
            }
        }
    }

    override fun onDestroyView() {
        fragmentBinding = null
        super.onDestroyView()
    }

}