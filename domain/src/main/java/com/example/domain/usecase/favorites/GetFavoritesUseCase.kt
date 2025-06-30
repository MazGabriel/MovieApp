package com.example.domain.usecase.favorites

import com.example.domain.model.Movie
import com.example.domain.repository.FavoriteRepository

class GetFavoritesUseCase(
    private val repository: FavoriteRepository
) {

    suspend operator fun invoke(): List<Movie> {
        return repository.getAll()
    }
}