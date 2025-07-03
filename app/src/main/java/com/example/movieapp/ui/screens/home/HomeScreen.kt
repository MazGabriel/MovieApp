package com.example.movieapp.ui.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.movieapp.ui.components.AutoScrollableMovieList
import com.example.movieapp.ui.navigation.Screen

@Composable
fun HomeScreen(navController: NavController, viewModel: MovieViewModel = hiltViewModel()) {
    LaunchedEffect(Unit) {
        viewModel.loadNextPage()
    }
    AutoScrollableMovieList(
        viewModel = viewModel,
    ) { movieId ->
        navController.navigate(Screen.MovieDetail.createRoute(movieId))
    }
}