package com.example.data.remote.dto


import com.example.core.utils.value
import com.example.domain.model.Movie
import com.google.gson.annotations.SerializedName

data class MovieDto(
    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("genre_ids")
    val genreIds: List<Int>?,
    val genres: List<GenreDto>?,
    val id: Int,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_title")
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int
) {
    fun toDomain() = Movie(
        adult = adult,
        backdropPath = backdropPath.value(),
        genreIds = genreIds.orEmpty(),
        genres = genres?.map { it.toDomain() }.orEmpty(),
        id = id,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath.value(),
        releaseDate = releaseDate,
        title = title,
        video = video.value(),
        voteAverage = voteAverage,
        voteCount = voteCount
    )
}