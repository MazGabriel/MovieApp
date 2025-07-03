package com.example.domain.usecase.favorites

import com.example.domain.model.Movie
import com.example.domain.repository.FavoriteRepository

class UpdateFavoritesUseCase(
    private val repository: FavoriteRepository
) {

    suspend operator fun invoke(movies: List<Movie>): List<Movie> {
        val favoriteIds = repository.getAllIds()
        return movies.map { movie -> movie.copy(isFavorite = favoriteIds.contains(movie.id)) }
    }
}