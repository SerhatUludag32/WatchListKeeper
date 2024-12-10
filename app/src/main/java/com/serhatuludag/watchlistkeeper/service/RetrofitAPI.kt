package com.serhatuludag.watchlistkeeper.service

import com.serhatuludag.watchlistkeeper.BuildConfig
import com.serhatuludag.watchlistkeeper.model.ImageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitAPI {

    @GET("/api/")
    suspend fun imageSearch(
        @Query("q") searchQuery: String,
        @Query("key") apiKey : String = BuildConfig.API_KEY) : Response<ImageResponse>

}