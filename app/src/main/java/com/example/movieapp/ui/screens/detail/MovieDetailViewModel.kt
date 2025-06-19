package com.example.movieapp.ui.screens.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Movie
import com.example.domain.usecase.GetMovieByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getMovieByIdUseCase: GetMovieByIdUseCase
) : ViewModel() {
    private val movieId: Int = checkNotNull(savedStateHandle["movieId"])
    private val _uiState = MutableStateFlow<MovieState>(MovieState.Loading)
    val uiState: StateFlow<MovieState> = _uiState

    init {
        getMovie()
    }

    private fun getMovie() {
        viewModelScope.launch {
            try {
                val movie = getMovieByIdUseCase(movieId)
                _uiState.value = MovieState.Success(movie)
            } catch (e: Exception) {
                _uiState.value = MovieState.Error(e.message ?: "Error")
            }
        }
    }
}

sealed class MovieState {
    data object Loading : MovieState()
    data class Success(val data: Movie) : MovieState()
    data class Error(val message: String) : MovieState()
}