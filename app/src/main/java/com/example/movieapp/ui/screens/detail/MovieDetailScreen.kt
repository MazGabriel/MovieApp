package com.example.movieapp.ui.screens.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import com.example.core.utils.response.ResponseState
import com.example.domain.model.Movie
import com.example.movieapp.ui.components.FavoriteButton
import com.example.movieapp.ui.components.MovieOverview
import com.example.movieapp.ui.components.MovieStatsRow

@Composable
fun MovieDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: MovieDetailViewModel = hiltViewModel(),
) {
    val movieState by viewModel.uiState.collectAsState()

    when (movieState) {
        is ResponseState.Loading -> CircularProgressIndicator(modifier.fillMaxSize())
        is ResponseState.Error -> Text("Error", color = MaterialTheme.colorScheme.error)
        is ResponseState.Success -> MovieDetail(
            movie = (movieState as ResponseState.Success).data, viewModel = viewModel
        )
    }
}

@Composable
fun MovieDetail(
    modifier: Modifier = Modifier, movie: Movie, viewModel: MovieDetailViewModel
) {
    val isFavorite by viewModel.isFavorite.collectAsState()

    Column(modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = movie.title,
            modifier = modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(Modifier.height(16.dp))
        Box {
            Image(
                painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .crossfade(true)
                        .data("${Constants.IMAGE_BASE_URL}${movie.posterPath}").build()
                ), contentDescription = "Movie image"
            )
            if (isFavorite)
                Icon(
                    modifier = modifier
                        .size(22.dp)
                        .padding(4.dp)
                        .align(alignment = Alignment.BottomEnd),
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Favorite",
                    tint = MaterialTheme.colorScheme.secondary,
                )
        }
        Spacer(modifier.height(16.dp))
        MovieStatsRow(
            voteAverage = movie.voteAverage,
            voteCount = movie.voteCount,
            language = movie.originalLanguage,
            isAdult = movie.adult
        )
        Spacer(modifier.height(16.dp))
        Text(
            text = movie.genres,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.tertiaryContainer
        )
        Spacer(modifier.height(16.dp))
        MovieOverview(overview = movie.overview)
        Spacer(modifier.height(16.dp))
        FavoriteButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            isFavorite = isFavorite,
            onClick = { viewModel.toggleFavorite() }
        )
    }
}