package com.example.data.remote.dto

import Genre

data class GenreDto(
    val id: Int,
    val name: String
) {
    fun toDomain() = Genre(
        id = id,
        name = name
    )
}