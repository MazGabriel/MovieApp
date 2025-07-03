package com.example.movieapp.ui.screens.favorites

import androidx.lifecycle.viewModelScope
import com.example.core.utils.response.onSuccess
import com.example.domain.usecase.favorites.GetFavoritesUseCase
import com.example.movieapp.ui.screens.BaseMovieViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoritesUseCase: GetFavoritesUseCase
) : BaseMovieViewModel() {

    override fun loadNextPage() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            try {
                getFavoritesUseCase().onSuccess { movies ->
                    state = state.copy(movies = movies, isLoading = false)
                }
            } catch (e: Exception) {
                state = state.copy(error = e.message ?: "Unknown Error", isLoading = false)
            }
        }
    }
}