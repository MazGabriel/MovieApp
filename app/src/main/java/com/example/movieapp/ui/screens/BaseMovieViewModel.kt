package com.example.movieapp.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.domain.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class BaseMovieViewModel @Inject constructor() : ViewModel() {

    protected var currentPage = 0
    var state by mutableStateOf(MovieState())
        protected set

    protected fun updateState(update: MovieState.() -> MovieState) {
        state = state.update()
    }

    open fun loadNextPage() {}
}

data class MovieState(
    val movies: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = "",
)