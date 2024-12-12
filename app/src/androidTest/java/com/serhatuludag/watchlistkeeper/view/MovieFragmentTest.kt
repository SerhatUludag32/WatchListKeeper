package com.serhatuludag.watchlistkeeper.view

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.MediumTest
import com.serhatuludag.watchlistkeeper.R
import com.serhatuludag.watchlistkeeper.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import javax.inject.Inject

@MediumTest
@HiltAndroidTest
class MovieFragmentTest  {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var fragmentFactory: MovieFragmentFactory

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun testNavigationFromMovieFragmentToMovieDetailFragment() {
        // mock used for creating a fake NavController
        val navController = mock(NavController::class.java)

            launchFragmentInHiltContainer<MovieFragment>(factory = fragmentFactory) {
                Navigation.setViewNavController(requireView(), navController)
            }
        // Espresso used for clicking on the FAB
        Espresso.onView(ViewMatchers.withId(R.id.fab)).perform(ViewActions.click())

        Mockito.verify(navController).navigate(
            MovieFragmentDirections.actionMovieFragmentToMovieDetailsFragment()
        )

    }
}