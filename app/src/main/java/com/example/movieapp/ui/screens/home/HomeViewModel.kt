package com.example.movieapp.ui.screens.home

import androidx.lifecycle.viewModelScope
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
        if (state.isLoading || state.endReached) return
        viewModelScope.launch {
            updateState { copy(isLoading = true) }
            currentPage++
            try {
                val newMovies = getPopularMovies(currentPage + 1)
                val newMoviesUpdated = updateFavorites(state.movies + newMovies)
                updateState {
                    copy(
                        movies = newMoviesUpdated,
                        isLoading = false,
                        endReached = newMovies.isEmpty()
                    )
                }
                if (newMovies.isNotEmpty()) currentPage++
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