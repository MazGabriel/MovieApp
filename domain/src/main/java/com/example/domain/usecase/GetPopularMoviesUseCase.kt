package com.example.domain.usecase

import com.example.domain.model.Movie
import com.example.domain.repository.MovieRepository

class GetPopularMoviesUseCase(
    private val repository: MovieRepository
) {

    suspend operator fun invoke(): List<Movie> {
        return repository.getPopularMovies()
    }
}