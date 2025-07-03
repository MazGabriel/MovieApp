package com.example.core.utils.response

inline fun <T> ResponseState<T>.onSuccess(action: (T) -> Unit) {
    if (this is ResponseState.Success) {
        action(data)
    }
}