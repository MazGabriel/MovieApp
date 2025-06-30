package com.example.domain.usecase.movies

import com.example.domain.model.Movie
import com.example.domain.usecase.favorites.GetFavoritesIdsUseCase

class GetMoviesWithFavoritesUseCase(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val getFavoritesIdsUseCase: GetFavoritesIdsUseCase
) {
    suspend operator fun invoke(): List<Movie> {
        val movies = getMoviesUseCase()
        val favoriteIds = getFavoritesIdsUseCase()
        return movies.map { movie ->
            movie.copy(isFavorite = favoriteIds.contains(movie.id))
        }
    }
}