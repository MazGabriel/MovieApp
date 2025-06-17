package com.example.data.remote.dto

import com.google.gson.annotations.SerializedName

data class PopularMoviesResponseDto(
    @SerializedName("results") val results: List<MovieDto>
)
