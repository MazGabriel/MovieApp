package com.example.movieapp.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.movieapp.ui.components.MoviesList
import com.example.movieapp.ui.navigation.Screen

@Composable
fun HomeScreen(navController: NavController, viewModel: MovieViewModel = hiltViewModel()) {
    val state = viewModel.state

    when {
        state.isLoading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        state.error.isNotEmpty() -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = state.error)
            }
        }

        else -> {
            MoviesList(movies = state.movies) { movieId ->
                navController.navigate(Screen.MovieDetail.createRoute(movieId))
            }
        }
    }
}