@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.movieapp.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import com.example.movieapp.ui.navigation.Screen

@Composable
fun MovieTopBar(
    title: String,
    currentRoute: String?,
    onBackClick: () -> Unit,
) {
    val showBackButton = Screen.bottomNavRoutes.none { routePattern ->
        currentRoute?.startsWith(routePattern) == true
    }

    TopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge
            )
        },
        navigationIcon = {
            if (showBackButton) {
                IconButton(onClick = { onBackClick() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        }
    )
}