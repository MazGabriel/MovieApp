package com.example.movieapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.request.transformations
import coil3.transform.RoundedCornersTransformation
import com.example.core.utils.Constants
import com.example.domain.model.Movie

@Composable
fun Movie(modifier: Modifier = Modifier, movie: Movie, onItemClicked: (Int) -> Unit) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable {
                onItemClicked(movie.id)
            },
        shape = RoundedCornerShape(corner = CornerSize(8.dp)),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(
            modifier = modifier.background(MaterialTheme.colorScheme.primary.copy(alpha = 0.3f))
        ) {
            Text(
                modifier = modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth(),
                text = movie.title,
                style = MaterialTheme.typography.titleSmall,
                minLines = 2,
                maxLines = 2,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis
            )
            Surface(
                shape = RoundedCornerShape(corner = CornerSize(8.dp))
            ) {
                Image(
                    painter = rememberAsyncImagePainter(
                        model = ImageRequest.Builder(LocalContext.current)
                            .crossfade(true)
                            .transformations(RoundedCornersTransformation(radius = 20f))
                            .data("${Constants.IMAGE_BASE_URL}${movie.posterPath}").build()
                    ), contentDescription = "Movie image"
                )
            }
        }
    }
}

@Composable
fun MoviesList(modifier: Modifier = Modifier, movies: List<Movie>, onItemClicked: (Int) -> Unit) {
    println("+++ ${MaterialTheme.colorScheme}")
    LazyVerticalGrid(
        modifier = modifier.fillMaxSize(),
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
    ) {
        items(movies.size) {
            Movie(movie = movies[it], onItemClicked = onItemClicked)
        }
    }
}