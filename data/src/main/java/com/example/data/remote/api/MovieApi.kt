package com.example.data.remote.api

import com.example.core.utils.Constants
import com.example.data.remote.dto.PopularMoviesResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String = Constants.API_KEY): PopularMoviesResponseDto
}