package com.example.movieapp.ui.screens.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.utils.response.ResponseState
import com.example.domain.model.Movie
import com.example.domain.usecase.favorites.AddFavoriteUseCase
import com.example.domain.usecase.favorites.IsFavoriteUseCase
import com.example.domain.usecase.favorites.RemoveFavoriteUseCase
import com.example.domain.usecase.movies.GetMovieByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getMovieByIdUseCase: GetMovieByIdUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val removeFavoriteUseCase: RemoveFavoriteUseCase,
    private val isFavoriteUseCase: IsFavoriteUseCase

) : ViewModel() {
    private val movieId: Int = savedStateHandle["movieId"] ?: 0
    private val _isFavorite = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> = _isFavorite
    private val _uiState = MutableStateFlow<ResponseState<Movie>>(ResponseState.Loading)
    val uiState: StateFlow<ResponseState<Movie>> = _uiState

    init {
        getMovie()
    }

    private fun getMovie() {
        viewModelScope.launch {
            _uiState.value = ResponseState.Loading
            try {
                val movie = getMovieByIdUseCase(movieId)
                checkFavorite()
                _uiState.value = movie
            } catch (e: Exception) {
                _uiState.value = ResponseState.Error(e.message ?: "Error")
            }
        }
    }

    private fun checkFavorite() {
        viewModelScope.launch {
            _isFavorite.value = isFavoriteUseCase(movieId)
        }
    }

    fun toggleFavorite() {
        viewModelScope.launch {
            if (_isFavorite.value) {
                removeFavoriteUseCase(movieId)
                _isFavorite.value = false
            } else {
                val movie = getMovieByIdUseCase(movieId)
                if (movie is ResponseState.Success) {
                    addFavoriteUseCase(movie.data)
                    _isFavorite.value = true
                }
            }
        }
    }
}