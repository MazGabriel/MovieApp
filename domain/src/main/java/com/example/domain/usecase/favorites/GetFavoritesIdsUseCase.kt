package com.example.domain.usecase.favorites

import com.example.core.utils.response.ResponseState
import com.example.domain.repository.FavoriteRepository

class GetFavoritesIdsUseCase(
    private val repository: FavoriteRepository
) {

    suspend operator fun invoke(): ResponseState<List<Int>> {
        return try {
            val result = repository.getAllIds()
            ResponseState.Success(result)
        } catch (e: Exception) {
            ResponseState.Error(e.message ?: "Error getting favorites")
        }
    }
}