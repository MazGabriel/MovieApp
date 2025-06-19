package com.example.movieapp.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.movieapp.ui.navigation.Screen

@Composable
fun BottomNavigationBar(navController: NavController, currentRoute: String) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.primaryContainer
    ) {
        Screen.bottomNavItems.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.3f),        // Active item background
                    selectedIconColor = MaterialTheme.colorScheme.secondary,                        // Selected icon
                    selectedTextColor = MaterialTheme.colorScheme.secondary,                        // Selected text
                    unselectedIconColor = MaterialTheme.colorScheme.secondary,                      // Not selected icon
                    unselectedTextColor = MaterialTheme.colorScheme.secondary                       // Not selected text
                ),
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                icon = { Icon(item.icon, contentDescription = item.title) },
                label = { Text(text = item.title) }
            )
        }
    }
}