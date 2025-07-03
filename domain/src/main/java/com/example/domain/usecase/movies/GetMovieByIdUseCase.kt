package com.example.domain.usecase.movies

import com.example.core.utils.response.ResponseState
import com.example.domain.model.Movie
import com.example.domain.repository.MovieRepository

class GetMovieByIdUseCase(
    private val repository: MovieRepository
) {

    suspend operator fun invoke(movieId: Int): ResponseState<Movie> {
        return try {
            val result = repository.getMoviesById(movieId)
            ResponseState.Success(result)
        } catch (e: Exception) {
            ResponseState.Error(e.message ?: "Error getting movie by id")
        }
    }
}