package com.serhatuludag.watchlistkeeper.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.MediumTest
import com.google.common.truth.Truth.assertThat
import com.serhatuludag.watchlistkeeper.R
import com.serhatuludag.watchlistkeeper.adapter.ImageRecyclerAdapter
import com.serhatuludag.watchlistkeeper.getOrAwaitValue
import com.serhatuludag.watchlistkeeper.launchFragmentInHiltContainer
import com.serhatuludag.watchlistkeeper.repo.FakeMovieRepositoryAndroidTest
import com.serhatuludag.watchlistkeeper.viewmodel.MovieViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import javax.inject.Inject

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class ImageApiFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    lateinit var fragmentFactory: MovieFragmentFactory

    @Before
    fun setup() {
        hiltRule.inject()
    }

//    @Test
//    fun selectImage() {
//        val navController = mock(NavController::class.java)
//        val testViewModel = MovieViewModel(FakeMovieRepositoryAndroidTest())
//        val selectedImageURL = "testimage.com"
//
//        launchFragmentInHiltContainer<ImageApiFragment>(factory = fragmentFactory) {
//            Navigation.setViewNavController(requireView(), navController)
//            viewModel = testViewModel
//            imageRecyclerAdapter.images = listOf(selectedImageURL)
//        }
//
//        Espresso.onView(ViewMatchers.withId(R.id.imageRecyclerView))
//                .perform(RecyclerViewActions
//                .actionOnItemAtPosition<ImageRecyclerAdapter.ImageViewHolder>(0, ViewActions.click()))
//
//        Mockito.verify(navController).popBackStack()
//        assertThat(testViewModel.selectedImageUrl.getOrAwaitValue()).isEqualTo(selectedImageURL)
//    }
//
//}