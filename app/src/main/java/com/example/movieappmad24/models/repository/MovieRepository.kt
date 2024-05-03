package com.example.movieappmad24.models

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MovieRepository {
    private val _movies = MutableStateFlow<List<Movie>>(getMovies())
    val movies: StateFlow<List<Movie>> = _movies

    fun getMovieById(id: String): Movie? {
        return _movies.value.find { it.id == id }
    }

    fun toggleFavorite(movie: Movie) {
        val currentMovies = _movies.value.toMutableList()
        val index = currentMovies.indexOfFirst { it.id == movie.id }
        if (index != -1) {
            val newMovie = currentMovies[index].copy(isFavorite = !currentMovies[index].isFavorite)
            currentMovies[index] = newMovie
            _movies.value = currentMovies
        }
    }
}
