import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappmad24.models.Movie
import com.example.movieappmad24.models.MovieRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class MovieDetailViewModel(private val repository: MovieRepository) : ViewModel() {
    private val _selectedMovie = MutableStateFlow<Movie?>(null)
    val selectedMovie: StateFlow<Movie?> = _selectedMovie

    val favoriteMovies: StateFlow<List<Movie>> = repository.movies.map { movies ->
        movies.filter { it.isFavorite }
    }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun getMovieById(id: String) {
        _selectedMovie.value = repository.getMovieById(id)
    }

    fun toggleFavorite(movie: Movie) {
        repository.toggleFavorite(movie)
    }
}
