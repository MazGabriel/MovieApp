package com.example.data.mapper

import com.example.data.local.entity.FavoriteMovieEntity
import com.example.domain.model.Movie

fun Movie.toFavorite(): FavoriteMovieEntity = FavoriteMovieEntity(
    id = id,
    title = title,
    posterPath = posterPath,
    voteAverage = voteAverage,
    adult = adult,
    backdropPath = backdropPath,
    genres = genres,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    overview = overview,
    popularity = popularity,
    releaseDate = releaseDate,
    video = video,
    voteCount = voteCount
)

fun FavoriteMovieEntity.toDomain(): Movie = Movie(
    id = id,
    title = title,
    posterPath = posterPath,
    voteAverage = voteAverage,
    adult = adult,
    backdropPath = backdropPath,
    genres = genres,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    overview = overview,
    popularity = popularity,
    releaseDate = releaseDate,
    video = video,
    voteCount = voteCount
)