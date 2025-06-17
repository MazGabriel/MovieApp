package com.example.data.repository

import com.example.data.remote.api.MovieApi
import com.example.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: MovieApi
) : MovieRepository {

    override suspend fun getPopularMovies(): List<com.example.domain.model.Movie> {
        return api.getPopularMovies().results.map { it.toDomain() }
    }
}