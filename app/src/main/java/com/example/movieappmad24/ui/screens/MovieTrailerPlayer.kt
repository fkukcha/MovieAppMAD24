
import android.content.Context
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
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

    AndroidView({ context ->
        PlayerView(context).apply {
            player = exoPlayer
        }
    }, modifier = Modifier
        .fillMaxWidth()
        .height(300.dp)
    )

    DisposableEffect(Unit) {
        onDispose {
            exoPlayer.release()
        }
    }

    // Lifecycle-aware ExoPlayer
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    DisposableEffect(lifecycle) {
        val lifecycleObserver = object : LifecycleObserver {
            @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
            fun onResume() {
                exoPlayer.playWhenReady = true
            }

            @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
            fun onStop() {
                exoPlayer.playWhenReady = false
            }
        }

        lifecycle.addObserver(lifecycleObserver)

        onDispose {
            lifecycle.removeObserver(lifecycleObserver)
        }
    }
}
