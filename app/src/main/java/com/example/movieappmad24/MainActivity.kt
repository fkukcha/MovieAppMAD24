package com.example.movieappmad24

import MovieListViewModel
import MovieListViewModelFactory
import MovieViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.movieappmad24.models.MovieRepository
import com.example.movieappmad24.navigation.Navigation
import com.example.movieappmad24.ui.theme.MovieAppMAD24Theme

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: MovieListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = MovieRepository() // Replace this with your actual repository instance
        val viewModelFactory = MovieListViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MovieListViewModel::class.java)

        setContent {
            MovieAppMAD24Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.onPrimary
                ) {
                    val viewModel: MovieViewModel by viewModels()
                    Navigation(viewModel)
                }
            }
        }
    }
}
