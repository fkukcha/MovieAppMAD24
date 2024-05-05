package com.example.movieappmad24.viewmodelfactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieappmad24.data.MovieRepository
import com.example.movieappmad24.viewmodels.MovieDetailViewModel

class MovieDetailViewModelFactory(
    private val repository: MovieRepository,
    private val movieId: String
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieDetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MovieDetailViewModel(repository, movieId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
