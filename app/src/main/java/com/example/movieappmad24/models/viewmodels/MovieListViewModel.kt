import androidx.lifecycle.ViewModel
import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.models.MovieRepository
import kotlinx.coroutines.flow.StateFlow

class MovieListViewModel(private val repository: MovieRepository) : ViewModel() {
    val movies: StateFlow<List<Movie>> = repository.movies

    fun toggleFavorite(movie: Movie) {
        repository.toggleFavorite(movie)
    }
}
