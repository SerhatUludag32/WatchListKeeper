package com.serhatuludag.watchlistkeeper.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.serhatuludag.watchlistkeeper.MainCoroutineRule
import com.serhatuludag.watchlistkeeper.getOrAwaitValue
import com.serhatuludag.watchlistkeeper.model.Movie
import com.serhatuludag.watchlistkeeper.repo.FakeMovieRepository
import com.serhatuludag.watchlistkeeper.util.Status
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: MovieViewModel

    @Before
    fun setup() {
        // Test Doubles
        viewModel = MovieViewModel(FakeMovieRepository())
    }

    @Test
    fun `insert art without year return error` () {
        viewModel.makeMovie("The Batman","Matt Reeves","")
        val value = viewModel.insertMovieMessage.getOrAwaitValue()
        assertThat(value.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert art without name return error` (){
        viewModel.makeMovie("","Matt Reeves","1111")
        val value = viewModel.insertMovieMessage.getOrAwaitValue()
        assertThat(value.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert art without director return error` (){
        viewModel.makeMovie("The Batman","","1111")
        val value = viewModel.insertMovieMessage.getOrAwaitValue()
        assertThat(value.status).isEqualTo(Status.ERROR)
    }


}