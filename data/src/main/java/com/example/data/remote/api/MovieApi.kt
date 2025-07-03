package com.example.data.remote.api

import com.example.data.remote.dto.MovieDto
import com.example.data.remote.dto.MoviesDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int = 1
    ): MoviesDto

    @GET("movie/{movie_id}")
    suspend fun getMoviesById(@Path("movie_id") movieId: Int): MovieDto
}