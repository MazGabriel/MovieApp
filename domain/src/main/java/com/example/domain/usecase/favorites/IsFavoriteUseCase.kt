package com.example.domain.usecase.favorites

import com.example.domain.repository.FavoriteRepository

class IsFavoriteUseCase(
    private val repository: FavoriteRepository
) {

    suspend operator fun invoke(id: Int): Boolean =
        repository.isFavorite(id)
}