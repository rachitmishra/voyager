@file:OptIn(ExperimentalMaterial3Api::class)

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.voyager.movies.domain.MovieItem
import com.voyager.movies.domain.MovieItems
import com.voyager.movies.domain.QueryItems
import com.voyager.movies.ui.Action
import com.voyager.movies.ui.MoviePage
import com.voyager.movies.ui.MovieViewModel
import com.voyager.movies.ui.PageError
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun <T> debounce(
    waitMs: Long = 300L, scope: CoroutineScope, destinationFunction: (T) -> Unit
): (T) -> Unit {
    var debounceJob: Job? = null
    return { param: T ->
        debounceJob?.cancel()
        debounceJob = scope.launch {
            delay(waitMs)
            destinationFunction(param)
        }
    }
}

@Composable
fun MovieApp(viewModel: MovieViewModel) {
    val state by remember { viewModel.state }
    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    val onQueryChange: (String) -> Unit = { query ->
        viewModel.perform(Action.Find(query))
    }

    val debouncedQuery = debounce(200L, coroutineScope, onQueryChange)

    val onEndReachedPopularMovies: () -> Unit = {
        viewModel.perform(Action.PaginatePopular)
    }

    val onErrorShown: (PageError) -> Unit = {}

    Column {
        Text(
            text = "Voyager", style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(12.dp, 8.dp)
                .fillMaxWidth(),
            color = MaterialTheme.colorScheme.onBackground
        )

        when (state) {
            is MoviePage.Content -> Content(
                state as MoviePage.Content, onEndReachedPopularMovies, onQueryChange, onErrorShown
            )

            is MoviePage.NoContent -> NoContent(state as MoviePage.NoContent, onErrorShown)
        }
    }
}

@Composable
fun ErrorBox(msg: String) {
    Box(
        contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()
    ) {
        Text(text = msg)
    }
}

@Composable
fun ProgressScreen() {
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator()
    }
}


@Composable
fun NoContent(
    state: MoviePage.NoContent, onErrorShown: (error: PageError) -> Unit
) {
    if (state.loading) {
        ProgressScreen()
        return
    }

    if (state.error == PageError.ConnectionError) {
        ErrorBox("No connection")
        onErrorShown(state.error)
        return
    }

    if (state.error is PageError.ApiError) {
        ErrorBox(state.error.message)
        onErrorShown(state.error)
        return
    }
}

@Composable
fun Content(
    state: MoviePage.Content,
    onEndReachedPopularMovies: (() -> Unit)? = null,
    onQueryChange: ((String) -> Unit),
    onErrorShown: (error: PageError) -> Unit
) {
    if (state.loading) {
        ProgressScreen()
    }

    if (state.error == PageError.ConnectionError) {
        ErrorBox("No connection")
        onErrorShown(state.error)
    }

    Column(modifier = Modifier.padding(8.dp)) {
        MovieRow("Popular", state.popular, onEndReachedPopularMovies)
        MovieRow("Now Playing", state.nowPlaying, itemType = 1)
        MovieRow("Top Rated", state.topRated, itemType = 1)
        SearchBox(state.search, onQueryChange)
    }
}

@Composable
fun SearchBox(query: QueryItems, onQuery: (String) -> Unit) {
    TextField(
        value = query.query,
        onValueChange = onQuery,
        singleLine = true,
        placeholder = { Text("Find movies ...") },
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(36.dp),
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        textStyle = MaterialTheme.typography.bodyMedium
    )
    if (query.movies.isNotEmpty()) {
        MovieRowWithTitle("Search Results", query.movies, query.loading)
    }
}

@Composable
fun MovieRow(title: String, state: MovieItems, onEndReachedPopularMovies: (() -> Unit)? = null, itemType: Int = 0) {
    if (state.movies.isNotEmpty()) {
        MovieRowWithTitle(title, state.movies, state.loading, state.error, onEndReachedPopularMovies, itemType)
    }
}

@Composable
fun MovieRowWithTitle(
    title: String,
    trending: List<MovieItem>,
    isLoading: Boolean,
    error: PageError? = null,
    onEndReachedPopularMovies: (() -> Unit)? = null,
    itemType: Int = 0,
) {
    if (error != null) {
        ErrorBox(msg = error.msg)
    }
    val lastIndex = trending.lastIndex
    Row(modifier = Modifier.fillMaxWidth()) {
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp), contentPadding = PaddingValues(4.dp, 12.dp)) {
                itemsIndexed(trending) { index, item ->
                    if (index == lastIndex) {
                        onEndReachedPopularMovies?.invoke()
                    }
                    when (itemType) {
                        0 -> MovieItem(item)
                        1 -> MovieItem1(item)
                    }
                }
            }
        }
        if (isLoading) {
            LoadingItem()
        }
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

@Composable
fun MovieItem(item: MovieItem) {
    Box(
        modifier = Modifier
            .height(120.dp)
            .width(200.dp)
            .clip(RoundedCornerShape(8.dp)),
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current).data(item.image).size(Size.ORIGINAL).crossfade(true).build()
            ), contentDescription = null, contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize()
        )
        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomStart
        ) {
            Surface(
                color = Color("#55111111".toColorInt()), modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = item.title, modifier = Modifier.padding(8.dp), style = TextStyle(
                        color = Color.White
                    )
                )
            }
        }
    }
}

@Composable
fun MovieItem1(item: MovieItem) {
    Box(
        modifier = Modifier
            .height(200.dp)
            .width(120.dp)
            .clip(RoundedCornerShape(8.dp)),
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current).data(item.image).size(Size.ORIGINAL).crossfade(true).build()
            ), contentDescription = null, contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize()
        )
        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomStart
        ) {
            Surface(
                color = Color("#55111111".toColorInt()), modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = item.title, modifier = Modifier.padding(8.dp), style = TextStyle(
                        color = Color.White
                    )
                )
            }
        }
    }
}
