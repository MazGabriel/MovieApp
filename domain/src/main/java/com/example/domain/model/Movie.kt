package com.example.domain.model

import Genre

data class Movie(
    val adult: Boolean,
    val backdropPath: String = "",
    val genreIds: List<Int> = emptyList(),
    val genres: List<Genre> = emptyList(),
    val id: Int,
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