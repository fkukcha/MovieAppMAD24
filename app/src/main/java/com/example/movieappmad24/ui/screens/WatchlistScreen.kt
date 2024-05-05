package com.example.movieappmad24.ui.screens

import SimpleTopAppBar
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.viewmodels.WatchlistViewModel

@Composable
fun WatchlistScreen(viewModel: WatchlistViewModel, onMovieClick: (Movie) -> Unit) {
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
