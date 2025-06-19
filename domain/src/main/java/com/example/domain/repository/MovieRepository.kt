package com.example.domain.repository

import com.example.domain.model.Movie

interface MovieRepository {
    suspend fun getPopularMovies(): List<Movie>

    suspend fun getMoviesById(movieId: Int): Movie
}