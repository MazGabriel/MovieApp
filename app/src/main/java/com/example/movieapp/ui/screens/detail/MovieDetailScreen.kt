package com.example.movieapp.ui.screens.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.domain.model.Movie

@Composable
fun MovieDetailScreen(
    modifier: Modifier = Modifier,
    viewmodel: MovieDetailViewModel = hiltViewModel(),
) {
    val movieState by viewmodel.uiState.collectAsState()

    when (movieState) {
        is MovieState.Loading -> CircularProgressIndicator(modifier.fillMaxSize())
        is MovieState.Error -> Text("Error", color = MaterialTheme.colorScheme.error)
        is MovieState.Success -> MovieDetail(movie = (movieState as MovieState.Success).data)
    }
}

@Composable
fun MovieDetail(
    modifier: Modifier = Modifier,
    movie: Movie
) {
    Column(modifier.padding(16.dp)) {
        Text(text = movie.title, style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(8.dp))
        Text(text = movie.overview)
    }
}