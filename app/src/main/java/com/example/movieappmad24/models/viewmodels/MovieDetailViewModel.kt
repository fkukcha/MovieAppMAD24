package com.example.movieappmad24.viewmodels

import androidx.lifecycle.ViewModel
import com.example.movieappmad24.data.MovieRepository
import com.example.movieappmad24.models.Movie
import kotlinx.coroutines.flow.StateFlow

class MovieDetailViewModel(private val repository: MovieRepository, movieId: String) : ViewModel() {
    val movie: Movie? = repository.getMovieById(movieId)
    val favoriteMovies: StateFlow<List<Movie>> = repository.favoriteMovies

    fun toggleFavorite(movie: Movie) {
        repository.toggleFavorite(movie)
    }
}
