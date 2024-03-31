package com.example.movieappmad24.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.movieappmad24.MovieRow
import com.example.movieappmad24.TopAppBar
import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.models.getMovies

@Composable
fun WatchlistScreen(onMovieClick: (Movie) -> Unit) {
    Column {
        TopAppBar(title = "Your Watchlist")
        LazyColumn {
            items(getMovies()) { movie ->
                MovieRow(movie = movie, onMovieClick = onMovieClick)
            }
        }
    }
}
