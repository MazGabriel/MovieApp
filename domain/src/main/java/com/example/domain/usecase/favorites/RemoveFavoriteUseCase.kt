package com.example.domain.usecase.favorites

import com.example.domain.repository.FavoriteRepository

class RemoveFavoriteUseCase(
    private val repository: FavoriteRepository
) {

    suspend operator fun invoke(id: Int) =
        repository.removeFavorite(id)
}