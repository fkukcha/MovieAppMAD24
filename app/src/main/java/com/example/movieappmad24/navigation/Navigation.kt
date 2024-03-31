package com.example.movieappmad24.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.movieappmad24.BottomNavigationBar
import com.example.movieappmad24.DetailScreen
import com.example.movieappmad24.MovieList
import com.example.movieappmad24.TopAppBar
import com.example.movieappmad24.models.getMovies

@Composable
fun Navigation() {
    val navController = rememberNavController()
    var movieClicked by remember { mutableStateOf(false) }

    // Observe the NavController's back stack
    val currentDestination by navController.currentBackStackEntryAsState()

    // Update movieClicked state when the current destination changes
    LaunchedEffect(currentDestination) {
        movieClicked = currentDestination?.destination?.route != Screen.MovieList.route
    }

    Column(modifier = Modifier.fillMaxSize()) {
        if (!movieClicked) {
            TopAppBar(title = "Movie App")
        }
        NavHost(navController = navController, startDestination = Screen.MovieList.route, modifier = Modifier.weight(1f)) {
            composable(Screen.MovieList.route) {
                MovieList(movies = getMovies(), onMovieClick = { movie ->
                    movieClicked = true
                    navController.navigate(Screen.MovieDetail.withArgs(movie.id))
                })
            }
            composable(Screen.MovieDetail.route) { backStackEntry ->
                val movieId = backStackEntry.arguments?.getString("movieId")
                val movie = getMovies().find { it.id == movieId }
                if (movie != null) {
                    DetailScreen(movie = movie, onBack = {
                        movieClicked = false
                        navController.popBackStack()
                    })
                }
            }
            composable(Screen.Watchlist.route) {
                WatchlistScreen(onMovieClick = { movie ->
                    movieClicked = true
                    navController.navigate(Screen.MovieDetail.withArgs(movie.id))
                })
            }
        }
        BottomNavigationBar(navController)
    }
}
