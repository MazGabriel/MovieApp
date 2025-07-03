package com.example.domain.usecase.favorites

import com.example.domain.model.Movie
import com.example.domain.repository.FavoriteRepository

class AddFavoriteUseCase(
    private val repository: FavoriteRepository
) {

    suspend operator fun invoke(movie: Movie) {
        repository.addFavorite(movie)
    }
}