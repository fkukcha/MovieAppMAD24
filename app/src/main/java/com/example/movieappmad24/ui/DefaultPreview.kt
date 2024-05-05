import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.movieappmad24.data.MovieRepository
import com.example.movieappmad24.models.getMovies
import com.example.movieappmad24.ui.screens.MovieListScreen
import com.example.movieappmad24.ui.theme.MovieAppMAD24Theme
import com.example.movieappmad24.viewmodels.MovieDetailViewModel
import com.example.movieappmad24.viewmodels.MovieListViewModel

@Preview
@Composable
fun DefaultPreview() {
    // Create a simple MovieRepository with some default data
    val repository = MovieRepository()
    // Fill the repository with some movies
    repository.setMovies(getMovies())

    // Create a MovieListViewModel with the repository
    val simpleViewModel = MovieListViewModel(repository)

    MovieAppMAD24Theme {
        MovieListScreen(
            viewModel = simpleViewModel,
            detailViewModel = MovieDetailViewModel(repository, ""),
            onMovieClick = {},
            onFavoriteClick = {}
        )
    }
}
