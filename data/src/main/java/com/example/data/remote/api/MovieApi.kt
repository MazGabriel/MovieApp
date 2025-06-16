package com.example.data.remote.api

import com.example.core.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String = Constants.API_KEY)
}