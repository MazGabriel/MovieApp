package com.example.movieapp.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.core.utils.value
import com.example.movieapp.ui.components.BottomNavigationBar
import com.example.movieapp.ui.components.MovieTopBar
import com.example.movieapp.ui.navigation.Screen
import com.example.movieapp.ui.screens.detail.MovieDetailScreen
import com.example.movieapp.ui.screens.home.HomeScreen

@Composable
fun MovieApp() {
    val navController = rememberNavController()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    val currentTitle = remember(currentRoute) {
        Screen.bottomNavItems.find { it.route == currentRoute }?.title ?: "MovieApp"
    }

    val showBottomBar = Screen.bottomNavRoutes.any { routePattern ->
        currentRoute?.startsWith(routePattern) == true
    }

    Scaffold(
        topBar = {
            MovieTopBar(
                title = currentTitle,
                currentRoute
            ) {
                navController.popBackStack()
            }
        },
        bottomBar = {
            if (showBottomBar)
                BottomNavigationBar(
                    navController = navController,
                    currentRoute = currentRoute.value()
                )
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(padding)
        ) {
            composable(Screen.Home.route) { HomeScreen(navController) }
            composable(Screen.Favorites.route) {}
            composable(Screen.Profile.route) {}
            composable(
                route = "details/{movieId}",
                arguments = listOf(navArgument("movieId") { type = NavType.IntType })
            ) {
                MovieDetailScreen()
            }
        }
    }
}