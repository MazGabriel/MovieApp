package com.example.movieapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.NotInterested
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.core.utils.capitalize

@Composable
fun MovieStatsRow(
    modifier: Modifier = Modifier,
    voteAverage: Double,
    voteCount: Int,
    language: String,
    isAdult: Boolean,
    isFavorite: Boolean = false,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .width(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        MovieStatItem(
            icon = Icons.Default.Star,
            label = "Votes",
            value = "$voteAverage"
        )
        MovieStatItem(
            icon = Icons.Default.BarChart,
            label = "Count",
            value = "$voteCount"
        )
        MovieStatItem(
            icon = Icons.Default.Language,
            label = "Language",
            value = language.capitalize()
        )
        MovieStatItem(
            icon = if (isAdult) Icons.Default.NotInterested else Icons.Default.Check,
            label = "Adult",
            value = if (isAdult) "Adulto" else "G-rated"
        )
        MovieStatItem(
            icon = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
            label = "Favorite",
            isFavoriteIcon = true
        )
    }
}

@Composable
fun MovieStatItem(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    label: String, value: String = "",
    isFavoriteIcon: Boolean = false
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = if (isFavoriteIcon)
                modifier.size(40.dp)
            else modifier.size(20.dp),
            imageVector = icon,
            contentDescription = label,
            tint = MaterialTheme.colorScheme.secondary,
        )
        if (!isFavoriteIcon)
        Text(
            text = value,
            modifier = modifier.padding(top = 4.dp, bottom = 4.dp),
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Composable
fun MovieOverview(
    modifier: Modifier = Modifier,
    overview: String,
    maxChars: Int = 150
) {
    var isExpanded by remember { mutableStateOf(false) }
    val finalOverview =
        if (!isExpanded && overview.length > maxChars)
            overview.take(maxChars) + "..."
        else
            overview

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { isExpanded = !isExpanded }
    ) {
        Column(
            modifier = modifier.background(MaterialTheme.colorScheme.primaryContainer)
        ) {
            Text(
                text = finalOverview,
                modifier = Modifier.padding(8.dp),
                color = MaterialTheme.colorScheme.secondary
            )
            if (overview.length >= maxChars) {
                Text(
                    text = if (isExpanded) "Show less" else "Show more",
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun FavoriteButton(
    modifier: Modifier = Modifier,
    isFavorite: Boolean = false,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            contentColor = MaterialTheme.colorScheme.tertiaryContainer
        ),
        onClick = onClick,
        content = {
            Icon(
                imageVector = if (isFavorite) Icons.Default.Remove else Icons.Default.Add,
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
            Spacer(Modifier.width(8.dp))
            Text(if (isFavorite) "Remove favorite" else "Add to favorites")
        })
}