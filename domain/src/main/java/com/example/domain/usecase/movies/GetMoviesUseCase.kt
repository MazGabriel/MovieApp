package com.example.domain.usecase.movies

import com.example.core.utils.response.ResponseState
import com.example.domain.model.Movie
import com.example.domain.repository.MovieRepository

class GetMoviesUseCase(
    private val repository: MovieRepository
) {

    suspend operator fun invoke(page: Int): ResponseState<List<Movie>> {
        return try {
            val result = repository.getPopularMovies(page)
            ResponseState.Success(result)
        } catch (e: Exception) {
            ResponseState.Error(e.message ?: "Error getting movies")
        }
    }
}