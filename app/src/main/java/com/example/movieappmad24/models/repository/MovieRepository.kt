package com.example.movieappmad24.data

import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.models.getMovies
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MovieRepository {
    private val _movies = MutableStateFlow(getMovies())
    private val _favoriteMovies = MutableStateFlow<List<Movie>>(emptyList())

    val movies: StateFlow<List<Movie>> = _movies
    val favoriteMovies: StateFlow<List<Movie>> = _favoriteMovies

    fun toggleFavorite(movie: Movie) {
        val currentFavorites = _favoriteMovies.value.toMutableList()
        if (currentFavorites.contains(movie)) {
            currentFavorites.remove(movie)
        } else {
            currentFavorites.add(movie)
        }
        _favoriteMovies.value = currentFavorites
    }

    fun getMovieById(movieId: String): Movie? {
        return _movies.value.find { it.id == movieId }
    }

    fun setMovies(movies: List<Movie>) {
        _movies.value = movies
    }
}
