package com.example.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_movies")
data class FavoriteMovieEntity(
    @PrimaryKey
    val id: Int,
    val adult: Boolean,
    val backdropPath: String = "",
    val genres: String = "",
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String = "",
    val releaseDate: String,
    val title: String,
    val video: Boolean = false,
    val voteAverage: Double,
    val voteCount: Int
)