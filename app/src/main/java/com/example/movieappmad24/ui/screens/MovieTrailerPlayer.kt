
import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.movieappmad24.models.Movie
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView

@Composable
fun MovieTrailerPlayer(context: Context, movie: Movie) {
    val exoPlayer = remember {
        SimpleExoPlayer.Builder(context).build()
    }

    val rawResId = context.resources.getIdentifier(
        movie.trailer, "raw", context.packageName
    )

    val mediaItem = MediaItem.fromUri("android.resource://${context.packageName}/$rawResId")
    exoPlayer.setMediaItem(mediaItem)
    exoPlayer.prepare()

    Column {
        Text("Movie Trailer", style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(0.dp))
        AndroidView({ context ->
            PlayerView(context).apply {
                player = exoPlayer
            }
        }, modifier = Modifier
            .fillMaxWidth()
            .height(300.dp))
    }
}
