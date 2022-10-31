@file:OptIn(ExperimentalMaterial3Api::class)

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.voyager.movies.domain.MovieItems
import com.voyager.movies.ui.MovieViewModel
import com.voyager.movies.ui.components.MovieRowWithTitle
import com.voyager.movies.ui.screens.DetailPage
import com.voyager.movies.ui.screens.HomePage


@Composable
fun MovieApp(viewModel: MovieViewModel) {
    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val navController = rememberNavController()


    NavHost(navController = navController, startDestination = "home") {
        composable("home") { HomePage(viewModel) }
        composable("detail") { DetailPage() }
    }
}


@Composable
fun MovieRow(title: String, state: MovieItems, onEndReachedPopularMovies: (() -> Unit)? = null, itemType: Int = 0) {
    if (state.movies.isNotEmpty()) {
        MovieRowWithTitle(title, state.movies, state.loading, state.error, onEndReachedPopularMovies, itemType)
    }
}


@Composable
fun LoadingItem() {
    Box(
        modifier = Modifier
            .height(200.dp)
            .width(200.dp)
            .clip(RoundedCornerShape(8.dp)),
    ) {
        CircularProgressIndicator()
    }
}
