import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.movieappmad24.models.Movie
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem

class MovieTrailerViewModel(private val context: Context, private val movie: Movie) : ViewModel() {
    val exoPlayer = ExoPlayer.Builder(context).build()
    val rawResId: Int
    val mediaItem: MediaItem

    init {
        rawResId = context.resources.getIdentifier(
            movie.trailer, "raw", context.packageName
        )

        mediaItem = MediaItem.fromUri("android.resource://${context.packageName}/$rawResId")
        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.prepare()
    }
}
