package com.example.domain.usecase.favorites

import com.example.core.utils.response.ResponseState
import com.example.domain.model.Movie
import com.example.domain.repository.FavoriteRepository

class GetFavoritesUseCase(
    private val repository: FavoriteRepository
) {

    suspend operator fun invoke(): ResponseState<List<Movie>> {
        return try {
            val result = repository.getAll()
            ResponseState.Success(result)
        } catch (e: Exception) {
            ResponseState.Error(e.message ?: "Error getting favorites")
        }
    }
}