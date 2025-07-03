package com.example.domain.usecase.movies

import com.example.domain.model.Movie
import com.example.domain.repository.MovieRepository

class GetMoviesUseCase(
    private val repository: MovieRepository
) {

    suspend operator fun invoke(page: Int): List<Movie> {
        return repository.getPopularMovies(page)
    }
}