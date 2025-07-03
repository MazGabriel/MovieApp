package com.example.core.utils.response

sealed class ResponseState<out T> {
    data class Success<out T>(val data: T) : ResponseState<T>()
    data class Error(val message: String) : ResponseState<Nothing>()
    data object Loading : ResponseState<Nothing>()
}