
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.movieappmad24.navigation.Screen

@Composable
fun SimpleBottomAppBar(navController: NavController) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

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
            NavigationBarItem(label = "Home", icon = Icons.Default.Home, isSelected = currentRoute == Screen.MovieList.route) {
                navController.navigate(Screen.MovieList.route)
            }
            NavigationBarItem(label = "Watchlist", icon = Icons.Default.Star, isSelected = currentRoute == Screen.Watchlist.route) {
                navController.navigate(Screen.Watchlist.route)
            }
        }
    }
}
