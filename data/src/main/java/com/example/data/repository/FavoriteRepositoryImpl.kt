package com.example.data.repository

import com.example.data.local.dao.FavoriteMovieDao
import com.example.data.mapper.toDomain
import com.example.data.mapper.toFavorite
import com.example.domain.model.Movie
import com.example.domain.repository.FavoriteRepository
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
    private val dao: FavoriteMovieDao
) : FavoriteRepository {

    override suspend fun addFavorite(movie: Movie) {
        dao.insert(movie.toFavorite())
    }

    override suspend fun removeFavorite(movieId: Int) {
        dao.delete(movieId)
    }

    override suspend fun getAll(): List<Movie> =
        dao.getAll().map { it.toDomain() }

    override suspend fun getAllIds(): List<Int> =
        dao.getAllIds()

    override suspend fun isFavorite(movieId: Int): Boolean =
        dao.isFavorite(movieId)
}