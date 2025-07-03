package com.example.movieapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.core.utils.Constants
import com.example.domain.model.Movie
import com.example.movieapp.ui.screens.BaseMovieViewModel

@Composable
fun Movie(modifier: Modifier = Modifier, movie: Movie, onItemClicked: (Int) -> Unit) {
    Card(
        modifier = modifier
            .padding(12.dp)
            .fillMaxWidth()
            .clickable {
                onItemClicked(movie.id)
            },
        shape = RoundedCornerShape(corner = CornerSize(8.dp)),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(
            modifier = modifier.background(MaterialTheme.colorScheme.primary)
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, start = 6.dp, end = 4.dp, bottom = 2.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = movie.title,
                    modifier = modifier
                        .align(alignment = Alignment.CenterVertically)
                        .weight(1f),
                    style = MaterialTheme.typography.titleSmall,
                    minLines = 2,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            Box {
                Image(
                    painter = rememberAsyncImagePainter(
                        model = ImageRequest.Builder(LocalContext.current)
                            .crossfade(true)
                            .data("${Constants.IMAGE_BASE_URL}${movie.posterPath}").build()
                    ), contentDescription = "Movie image"
                )
                if (movie.isFavorite)
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
        }
    }
}

@Composable
fun ScrollableMovieList(
    modifier: Modifier = Modifier,
    movies: List<Movie> = emptyList(),
    onItemClicked: (Int) -> Unit = {}
) {
    LazyVerticalGrid(
        modifier = modifier.fillMaxSize(),
        columns = GridCells.Fixed(2),
    ) {
        items(movies.size) {
            Movie(movie = movies[it], onItemClicked = onItemClicked)
        }
    }
}

@Composable
fun AutoScrollableMovieList(
    modifier: Modifier = Modifier, viewModel: BaseMovieViewModel,
    onItemClicked: (Int) -> Unit = {}
) {
    val gridState = rememberLazyGridState()
    val movies by rememberUpdatedState(viewModel.state.movies)
    val isLoading by rememberUpdatedState(viewModel.state.isLoading)
    val error by rememberUpdatedState(viewModel.state.error)

    LaunchedEffect(gridState, movies) {
        if (movies.isEmpty()) return@LaunchedEffect
        snapshotFlow {
            gridState.layoutInfo.let { layout ->
                val lastItem = layout.visibleItemsInfo.lastOrNull()?.index ?: 0
                val totalItems = layout.totalItemsCount
                lastItem to totalItems
            }
        }.collect { (lastVisibleIndex, totalItems) ->
            if (lastVisibleIndex >= totalItems - 1 && !isLoading && error.isEmpty() && !viewModel.state.endReached) {
                viewModel.loadNextPage()
            }
        }
    }

    LazyVerticalGrid(
        modifier = modifier.fillMaxSize(),
        columns = GridCells.Fixed(2), state = gridState
    ) {
        itemsIndexed(movies) { index, movie ->
            Movie(movie = movie, onItemClicked = onItemClicked)
        }

        item(span = { GridItemSpan(2) }) {
            if (viewModel.state.isLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}
