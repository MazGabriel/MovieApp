package com.example.domain.usecase.favorites

import com.example.domain.repository.FavoriteRepository

class GetFavoritesIdsUseCase(
    private val repository: FavoriteRepository
) {

    suspend operator fun invoke(): List<Int> {
        return repository.getAllIds()
    }
}