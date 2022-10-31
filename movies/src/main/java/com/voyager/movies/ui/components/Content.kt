package com.voyager.movies.ui.components

import MovieRow
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.voyager.movies.ui.state.MoviePage
import com.voyager.movies.ui.state.PageError

@ExperimentalMaterial3Api
@Composable
fun Content(
    state: MoviePage.Content,
    onEndReachedPopularMovies: (() -> Unit)? = null,
    onQueryChange: ((String) -> Unit),
    onErrorShown: (error: PageError) -> Unit
) {
    val scrollableState = rememberScrollState()
    if (state.loading) {
        ProgressBox()
    }

    if (state.error == PageError.ConnectionError) {
        ErrorBox("No connection")
        onErrorShown(state.error)
    }

    Column(
        modifier = Modifier
            .padding(8.dp)
            .verticalScroll(scrollableState)
    ) {
        MovieRow("Popular", state.popular, onEndReachedPopularMovies)
        MovieRow("Now Playing", state.nowPlaying, itemType = 1)
        MovieRow("Top Rated", state.topRated, itemType = 1)
        SearchBox(state.search, onQueryChange)
    }
}
