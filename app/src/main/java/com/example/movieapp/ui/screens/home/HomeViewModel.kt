package com.example.movieapp.ui.screens.home

import androidx.lifecycle.viewModelScope
import com.example.core.utils.response.ResponseState
import com.example.core.utils.response.onSuccess
import com.example.domain.usecase.favorites.UpdateFavoritesUseCase
import com.example.domain.usecase.movies.GetMoviesUseCase
import com.example.movieapp.ui.screens.BaseMovieViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getPopularMovies: GetMoviesUseCase,
    private val updateFavorites: UpdateFavoritesUseCase
) : BaseMovieViewModel() {

    override fun loadNextPage() {
        if (state.isLoading) return
        viewModelScope.launch {
            updateState { copy(isLoading = true) }
            currentPage++
            try {
                val newMovies = (getPopularMovies(currentPage + 1) as ResponseState.Success).data
                updateFavorites(state.movies + newMovies).onSuccess { favorites ->
                    updateState {
                        copy(
                            movies = favorites,
                            isLoading = false,
                        )
                    }
                    if (newMovies.isNotEmpty()) currentPage++
                }
            } catch (e: Exception) {
                updateState {
                    copy(
                        isLoading = false,
                        error = e.message ?: "Error loading movies"
                    )
                }
            }
        }
    }
}