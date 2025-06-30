package com.example.domain.repository

import com.example.domain.model.Movie

interface FavoriteRepository {

    suspend fun addFavorite(movie: Movie)

    suspend fun removeFavorite(movieId: Int)

    suspend fun getAll(): List<Movie>

    suspend fun getAllIds(): List<Int>

    suspend fun isFavorite(movieId: Int): Boolean
}