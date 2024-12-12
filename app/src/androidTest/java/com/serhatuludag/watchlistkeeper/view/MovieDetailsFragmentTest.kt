package com.serhatuludag.watchlistkeeper.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.MediumTest
import com.google.common.truth.Truth.assertThat
import com.serhatuludag.watchlistkeeper.R
import com.serhatuludag.watchlistkeeper.getOrAwaitValue
import com.serhatuludag.watchlistkeeper.launchFragmentInHiltContainer
import com.serhatuludag.watchlistkeeper.model.Movie
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
class MovieDetailsFragmentTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    lateinit var fragmentFactory: MovieFragmentFactory

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun testNavigationFromMovieDetailsFragmentToImageAPIFragment() {

        val navController = mock(NavController::class.java)

            launchFragmentInHiltContainer<MovieDetailsFragment>(factory = fragmentFactory) {
                Navigation.setViewNavController(requireView(), navController)
            }

        Espresso.onView(ViewMatchers.withId(R.id.movieImageView)).perform(ViewActions.click())

        Mockito.verify(navController).navigate(
            MovieDetailsFragmentDirections.actionMovieDetailsFragmentToImageApiFragment()
        )

    }

    @Test
    fun onBackButtonPressed() {
        val navController = mock(NavController::class.java)

        launchFragmentInHiltContainer<MovieDetailsFragment>(factory = fragmentFactory) {
            Navigation.setViewNavController(requireView(), navController)
        }

        Espresso.pressBack()

        Mockito.verify(navController).popBackStack()
    }

 //   @Test
//    fun saveTest() {
//        val testViewModel = MovieViewModel(FakeMovieRepositoryAndroidTest())
//        launchFragmentInHiltContainer<MovieDetailsFragment>(factory = fragmentFactory){
//            //viewModel = testViewModel
//        }
//
//
//
//        Espresso.onView(ViewMatchers.withId(R.id.movieNameText)).perform(ViewActions.replaceText("movieName"))
//        Espresso.onView(ViewMatchers.withId(R.id.ReleaseYearText)).perform(ViewActions.replaceText("2024"))
//        Espresso.onView(ViewMatchers.withId(R.id.directorNameText)).perform(ViewActions.replaceText("directorName"))
//        Espresso.onView(ViewMatchers.withId(R.id.movieImageView)).perform(ViewActions.click())
//
//        assertThat(testViewModel.movieList.getOrAwaitValue()).contains(
//            Movie(
//                "movieName",
//                "directorName",
//                2024,
//                ""
//            )
//        )
//
//    }
//
//}