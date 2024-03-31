package com.example.movieappmad24.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.movieappmad24.BottomNavigationBar
import com.example.movieappmad24.MovieRow
import com.example.movieappmad24.TopAppBar
import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.models.getMovies

@Composable
fun WatchlistScreen(onMovieClick: (Movie) -> Unit) {
    val navController = rememberNavController()

    Column {
        TopAppBar(title = "Watchlist")
        LazyColumn {
            items(getMovies()) { movie ->
                MovieRow(movie = movie, onMovieClick = onMovieClick)
            }
        }
        BottomNavigationBar(navController)
    }
}