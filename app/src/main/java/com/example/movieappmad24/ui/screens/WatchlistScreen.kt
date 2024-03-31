package com.example.movieappmad24.ui.screens

import SimpleTopAppBar
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.models.getMovies

@Composable
fun WatchlistScreen(onMovieClick: (Movie) -> Unit) {
    Column {
        SimpleTopAppBar(title = "Your Watchlist")
        LazyColumn {
            items(getMovies()) { movie ->
                MovieRow(movie = movie, onMovieClick = onMovieClick)
            }
        }
    }
}
