package com.example.data.repository

import com.example.data.remote.api.MovieApi
import com.example.domain.model.Movie
import com.example.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: MovieApi
) : MovieRepository {

    override suspend fun getPopularMovies(): List<Movie> {
        return api.getPopularMovies().results.map { it.toDomain() }
    }

    override suspend fun getMoviesById(movieId: Int): Movie {
        return api.getMoviesById(movieId).toDomain()
    }
}