package com.example.data.remote.dto

import com.example.domain.model.Movie
import com.google.gson.annotations.SerializedName

data class PopularMoviesResponseDto(
    @SerializedName("results") val results: List<MovieDto>
)

data class MovieDto(
    val id: Int,
    val title: String,
    @SerializedName("poster_path") val posterPath: String?
) {
    fun toDomain() = Movie(id, title, posterPath)
}
