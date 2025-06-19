package com.example.movieapp.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    data object Home : Screen("home", "Home", Icons.Default.Home)
    data object Favorites : Screen("favorites", "Favorites", Icons.Default.Favorite)
    data object Profile : Screen("profile", "Profile", Icons.Default.Person)
    data object MovieDetail : Screen("details/{movieId}", "MovieDetail", Icons.Default.Info) {
        fun createRoute(movieId: Int): String = "details/$movieId"
    }

    companion object {
        val bottomNavItems = listOf(Home, Favorites, Profile)
        val bottomNavRoutes = bottomNavItems.map { it.route }
    }
}