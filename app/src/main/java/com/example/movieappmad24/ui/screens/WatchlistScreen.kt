package com.example.movieappmad24.ui.screens

import MovieViewModel
import SimpleTopAppBar
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.movieappmad24.models.Movie

@Composable
fun WatchlistScreen(viewModel: MovieViewModel, onMovieClick: (Movie) -> Unit) {
    val favoriteMovies by viewModel.favoriteMovies.collectAsState()

    Column {
        SimpleTopAppBar(title = "Your Watchlist")
        LazyColumn {
            items(favoriteMovies) { movie ->
                MovieRow(movie = movie, favoriteMovies = favoriteMovies, onMovieClick = onMovieClick, onFavoriteClick = { viewModel.toggleFavorite(it) })
            }
        }
    }
}
