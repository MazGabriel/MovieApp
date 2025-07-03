package com.example.movieapp.ui.screens.favorites

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.movieapp.ui.components.EmptyListMessage
import com.example.movieapp.ui.components.ScrollableMovieList
import com.example.movieapp.ui.navigation.Screen

@Composable
fun FavoritesScreen(navController: NavController, viewModel: FavoritesViewModel = hiltViewModel()) {
    val state = viewModel.state

    LaunchedEffect(Unit) {
        viewModel.loadNextPage()
    }

    when {
        state.isLoading ->
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }

        state.error.isNotEmpty() || state.movies.isEmpty() ->
            EmptyListMessage(message = "No favorites found.")

        else ->
            ScrollableMovieList(
                movies = state.movies,
            ) { movieId ->
                navController.navigate(Screen.MovieDetail.createRoute(movieId))
            }
    }
}