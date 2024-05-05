package com.example.movieappmad24.ui.screens.detailscreen

import MovieTrailerPlayer
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.ui.screens.MovieRow
import com.example.movieappmad24.viewmodels.MovieDetailViewModel

@Composable
fun DetailScreen(movie: Movie, viewModel: MovieDetailViewModel, onBack: () -> Unit) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(color = Color(0xFFF4E6FF))
        ) {
            IconButton(onClick = { onBack() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back", tint = MaterialTheme.colorScheme.primary)
            }
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                color = MaterialTheme.colorScheme.primary
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .background(color = Color(0xFFF4E6FF))
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = movie.title,
                        style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.primary),
                        modifier = Modifier.padding(start = 10.dp)
                    )
                }
            }
        }
        val favoriteMovies by viewModel.favoriteMovies.collectAsState()
        MovieRow(movie, favoriteMovies = favoriteMovies, onMovieClick={}, onFavoriteClick = { viewModel.toggleFavorite(it) })

        MovieTrailerPlayer(LocalContext.current, movie)

        LazyRow {
            items(movie.images) { image ->
                Card(
                    modifier = Modifier
                        .padding(5.dp)
                        .size(150.dp),
                    shape = ShapeDefaults.Large,
                    elevation = CardDefaults.cardElevation(10.dp)
                ) {
                    Image(
                        painter = rememberImagePainter(
                            data = image,
                            builder = { crossfade(true) }
                        ),
                        contentDescription = "Movie Image",
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}
