
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieappmad24.models.Movie

class MovieTrailerViewModelFactory(private val context: Context, private val movie: Movie) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieTrailerViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MovieTrailerViewModel(context, movie) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}