import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.movieappmad24.models.getMovies
import com.example.movieappmad24.ui.screens.MovieList
import com.example.movieappmad24.ui.theme.MovieAppMAD24Theme

@Preview
@Composable
fun DefaultPreview(){
    MovieAppMAD24Theme {
        MovieList(movies = getMovies(), favoriteMovies = listOf(), onMovieClick = {}, onFavoriteClick = {})
    }
}
