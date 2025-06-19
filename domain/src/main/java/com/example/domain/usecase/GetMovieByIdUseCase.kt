package com.example.domain.usecase

import com.example.domain.model.Movie
import com.example.domain.repository.MovieRepository

class GetMovieByIdUseCase(
    private val repository: MovieRepository
) {

    suspend operator fun invoke(movieId: Int): Movie {
        return repository.getMoviesById(movieId)
    }
}