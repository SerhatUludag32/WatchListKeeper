package com.serhatuludag.watchlistkeeper.dependencyinjection

import android.content.Context
import androidx.room.Room
import com.serhatuludag.watchlistkeeper.roomdb.MovieDatabase
import com.serhatuludag.watchlistkeeper.service.RetrofitAPI
import com.serhatuludag.watchlistkeeper.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun injectRoomDatabase(@ApplicationContext context: Context)
    = Room.databaseBuilder(context,MovieDatabase::class.java,"MovieDB").build()

    @Singleton
    @Provides
    fun injectDao(database: MovieDatabase) = database.movieDao()

    @Singleton
    @Provides
    fun injectRetrofitAPI() : RetrofitAPI {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(RetrofitAPI::class.java)

    }

}