import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieappmad24.models.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class WatchlistViewModel : ViewModel() {
    private val _favoriteMovies = MutableStateFlow<List<Movie>>(emptyList())
    val favoriteMovies: StateFlow<List<Movie>> = _favoriteMovies

    fun toggleFavorite(movie: Movie) {
        viewModelScope.launch {
            val currentFavorites = _favoriteMovies.value.toMutableList()
            if (currentFavorites.contains(movie)) {
                currentFavorites.remove(movie)
            } else {
                currentFavorites.add(movie)
            }
            _favoriteMovies.emit(currentFavorites)
        }
    }
}
