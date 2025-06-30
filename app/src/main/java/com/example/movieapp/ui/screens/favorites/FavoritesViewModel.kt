package com.example.movieapp.ui.screens.favorites

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Movie
import com.example.domain.usecase.favorites.GetFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoritesUseCase: GetFavoritesUseCase
) : ViewModel() {
    var state by mutableStateOf(FavoritesState())
        private set

    fun fetchFavorites() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            try {
                val favorites = getFavoritesUseCase()
                state = state.copy(favorites = favorites, isLoading = false)
            } catch (e: Exception) {
                state = state.copy(error = e.message ?: "Unknown Error", isLoading = false)
            }
        }
    }
}

data class FavoritesState(
    val favorites: List<Movie> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)