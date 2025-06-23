package com.example.movieapp.ui.screens.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.core.utils.Constants
import com.example.domain.model.Movie
import com.example.movieapp.ui.components.MovieOverview
import com.example.movieapp.ui.components.MovieStatsRow

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
    val genresString = movie.genres.joinToString { it.name }

    Column(modifier.padding(16.dp)) {
        Text(
            text = movie.title,
            modifier = modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(Modifier.height(16.dp))
        Surface(modifier = modifier.fillMaxWidth()) {
            Image(
                painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .crossfade(true)
                        .data("${Constants.IMAGE_BASE_URL}${movie.posterPath}").build()
                ),
                contentDescription = "Movie image",
                modifier = modifier.background(MaterialTheme.colorScheme.background),
                alignment = Alignment.Center
            )
        }
        Spacer(Modifier.height(16.dp))

        MovieStatsRow(
            voteAverage = movie.voteAverage,
            voteCount = movie.voteCount,
            language = movie.originalLanguage,
            isAdult = movie.adult
        )
        Spacer(Modifier.height(16.dp))
        Text(
                text = genresString,
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.tertiaryContainer
        )
        Spacer(Modifier.height(16.dp))
        MovieOverview(overview = movie.overview)
    }
}