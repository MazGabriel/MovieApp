package com.example.domain.usecase.favorites

import com.example.core.utils.response.ResponseState
import com.example.domain.model.Movie
import com.example.domain.repository.FavoriteRepository

class UpdateFavoritesUseCase(
    private val repository: FavoriteRepository
) {

    suspend operator fun invoke(movies: List<Movie>): ResponseState<List<Movie>> {
        val favoriteIds = repository.getAllIds()
        return try {
            val result =
                movies.map { movie -> movie.copy(isFavorite = favoriteIds.contains(movie.id)) }
            ResponseState.Success(result)
        } catch (e: Exception) {
            ResponseState.Error(e.message ?: "Error updating favorites")
        }
    }
}