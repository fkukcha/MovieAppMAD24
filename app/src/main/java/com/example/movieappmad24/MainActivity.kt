package com.example.movieappmad24

import MovieListViewModelFactory
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.movieappmad24.data.MovieRepository
import com.example.movieappmad24.navigation.Navigation
import com.example.movieappmad24.ui.theme.MovieAppMAD24Theme
import com.example.movieappmad24.viewmodels.MovieListViewModel

class MainActivity : ComponentActivity() {
    private val repository = MovieRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppMAD24Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModelFactory = MovieListViewModelFactory(repository)
                    val movieListViewModel = ViewModelProvider(this, viewModelFactory).get(MovieListViewModel::class.java)
                    Navigation(movieListViewModel, repository)
                }
            }
        }
    }
}
