package com.example.movieappmad24.viewmodels

import androidx.lifecycle.ViewModel
import com.example.movieappmad24.data.MovieRepository
import com.example.movieappmad24.models.Movie
import kotlinx.coroutines.flow.StateFlow

class MovieListViewModel(private val repository: MovieRepository) : ViewModel() {
    val movies: StateFlow<List<Movie>> = repository.movies
    val favoriteMovies: StateFlow<List<Movie>> = repository.favoriteMovies

    fun toggleFavorite(movie: Movie) {
        repository.toggleFavorite(movie)
    }
}
