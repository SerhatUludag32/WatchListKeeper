package com.serhatuludag.watchlistkeeper.roomdb

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.serhatuludag.watchlistkeeper.getOrAwaitValue
import com.serhatuludag.watchlistkeeper.model.Movie
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@SmallTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
class MovieDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("testDatabase")
    lateinit var database: MovieDatabase

    private lateinit var dao: MovieDao

    @Before
    fun setup() {

//        database = Room.inMemoryDatabaseBuilder(  // inMemoryDatabaseBuilder() creates an in-memory (Temporary) database for testing
//             ApplicationProvider.getApplicationContext(), MovieDatabase::class.java)
//            .allowMainThreadQueries()
//            .build()

        hiltRule.inject()

        dao = database.movieDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertMovieAtNormalConditionsTest () = runBlocking{
        val movie = Movie("The Batman", "Matt Reeves", 2022,"test.com",1)
        dao.insertMovie(movie)

        val allMovies = dao.observeMovies().getOrAwaitValue()
        assertThat(allMovies).contains(movie)

    }

    @Test
    fun deleteMovieAtNormalConditionsTest () = runBlocking{
        val movie = Movie("The Batman", "Matt Reeves", 2022,"test.com",1)
        dao.insertMovie(movie)
        dao.deleteMovie(movie)

        val allMovies = dao.observeMovies().getOrAwaitValue()
        assertThat(allMovies).doesNotContain(movie)

    }
}