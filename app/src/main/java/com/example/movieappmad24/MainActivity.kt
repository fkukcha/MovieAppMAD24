package com.example.movieappmad24

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.models.getMovies
import com.example.movieappmad24.ui.theme.MovieAppMAD24Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppMAD24Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.onPrimary
                ) {
                    Column {
                        TopAppBar(title = "Movie App")
                        MovieList(movies = getMovies(), modifier = Modifier.weight(1f))
                        BottomNavigationBar()
                    }
                }
            }
        }
    }
}

@Composable
fun MovieList(movies: List<Movie> = getMovies(), modifier: Modifier = Modifier){
    LazyColumn(modifier = modifier) {
        items(movies) { movie ->
            MovieRow(movie)
        }
    }
}


@Composable
fun MovieRow(movie: Movie){
    var showDetails by remember {
        mutableStateOf(false)
    }

    val painter: Painter = rememberImagePainter(
        data = movie.images.first(),
        builder = {
            crossfade(true)
        }
    )

    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp),
        shape = ShapeDefaults.Large,
        elevation = CardDefaults.cardElevation(10.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painter,
                    contentScale = ContentScale.Crop,
                    contentDescription = "Movie Image")
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    contentAlignment = Alignment.TopEnd
                ){
                    Icon(
                        tint = MaterialTheme.colorScheme.secondary,
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "Add to favorites")
                }
            }
            
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = movie.title)
                Icon(modifier = Modifier
                    .clickable {
                       showDetails = !showDetails
                    },
                    imageVector =
                        if (showDetails) Icons.Filled.KeyboardArrowDown
                        else Icons.Default.KeyboardArrowUp, contentDescription = "show more")
            }

            AnimatedVisibility(visible = showDetails) {
                Column(modifier = Modifier.padding(6.dp)) {
                    Text(text = "Director: ${movie.director}")
                    Text(text = "Release: ${movie.year}")
                    Text(text = "Genre: ${movie.genre}")
                    Text(text = "Actors: ${movie.actors}")
                    Text(text = "Rating: ${movie.rating}")
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color.Gray))
                    Text(text = "Plot: ${movie.plot}")
                }
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview(){
    MovieAppMAD24Theme {
       MovieList(movies = getMovies())
    }
}

@Composable
fun TopAppBar(
    title: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
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
                text = title,
                style = MaterialTheme.typography.bodyLarge.copy(color = MaterialTheme.colorScheme.primary),
                modifier = Modifier.padding(start = 10.dp)
            )
        }
    }
}

@Composable
fun BottomNavigationBar() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        color = MaterialTheme.colorScheme.primary
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .background(color = Color(0xFFF4E6FF))
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            NavigationBarItem(label = "Home", icon = Icons.Default.Home)
            NavigationBarItem(label = "Watchlist", icon = Icons.Default.Star)
        }
    }
}

@Composable
fun NavigationBarItem(label: String, icon: ImageVector) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = MaterialTheme.colorScheme.secondary
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.secondary)
        )
    }
}
