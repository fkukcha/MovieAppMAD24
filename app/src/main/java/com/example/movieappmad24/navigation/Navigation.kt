package com.example.movieappmad24.navigation

import SimpleBottomAppBar
import SimpleTopAppBar
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.movieappmad24.data.MovieRepository
import com.example.movieappmad24.ui.screens.detailscreen.DetailScreen
import com.example.movieappmad24.ui.screens.MovieListScreen
import com.example.movieappmad24.ui.screens.WatchlistScreen
import com.example.movieappmad24.viewmodelfactories.MovieDetailViewModelFactory
import com.example.movieappmad24.viewmodelfactories.WatchlistViewModelFactory
import com.example.movieappmad24.viewmodels.MovieDetailViewModel
import com.example.movieappmad24.viewmodels.MovieListViewModel
import com.example.movieappmad24.viewmodels.WatchlistViewModel

@Composable
fun Navigation(
    movieListViewModel: MovieListViewModel,
    repository: MovieRepository
) {
    val navController = rememberNavController()
    var movieClicked by remember { mutableStateOf(false) }

    val currentDestination by navController.currentBackStackEntryAsState()
    LaunchedEffect(currentDestination) {
        movieClicked = currentDestination?.destination?.route != Screen.MovieList.route
    }

    Column(modifier = Modifier.fillMaxSize()) {
        if (!movieClicked) {
            SimpleTopAppBar(title = "Movie App")
        }

        NavHost(
            navController = navController,
            startDestination = Screen.MovieList.route,
            modifier = Modifier.weight(1f)
        ) {
            composable(Screen.MovieList.route) {
                MovieListScreen(
                    viewModel = movieListViewModel,
                    detailViewModel = MovieDetailViewModel(repository, ""),
                    onMovieClick = { movie ->
                        movieClicked = true
                        navController.navigate(Screen.MovieDetail.withArgs(movie.id))
                    },
                    onFavoriteClick = { movie ->
                        movieListViewModel.toggleFavorite(movie)
                    }
                )
            }

            composable(Screen.MovieDetail.route) { backStackEntry ->
                val movieId = backStackEntry.arguments?.getString("movieId")
                if (movieId != null) {
                    val viewModelFactory = MovieDetailViewModelFactory(repository, movieId)
                    val detailViewModel = ViewModelProvider(navController.context as ComponentActivity, viewModelFactory).get(
                        MovieDetailViewModel::class.java)

                    detailViewModel.movie?.let { movie ->
                        DetailScreen(
                            movie = movie,
                            viewModel = detailViewModel,
                            onBack = {
                                movieClicked = false
                                navController.popBackStack()
                            }
                        )
                    }
                }
            }

            composable(Screen.Watchlist.route) {
                val viewModelFactory = WatchlistViewModelFactory(repository)
                val watchlistViewModel = ViewModelProvider(navController.context as ComponentActivity, viewModelFactory).get(
                    WatchlistViewModel::class.java)

                WatchlistScreen(
                    viewModel = watchlistViewModel,
                    onMovieClick = { movie ->
                        movieClicked = true
                        navController.navigate(Screen.MovieDetail.withArgs(movie.id))
                    }
                )
            }
        }
        SimpleBottomAppBar(navController)
    }
}
