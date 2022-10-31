package com.voyager.movies.ui.screens

import androidx.compose.runtime.Composable
import com.voyager.movies.ui.MovieViewModel

@Composable
fun HomePage(
    viewModel: MovieViewModel
) {
    val state by remember { viewModel.state }

    val onQueryChange: (String) -> Unit = { query ->
        viewModel.perform(Action.Find(query))
    }

    val onEndReachedPopularMovies: () -> Unit = {
        viewModel.perform(Action.PaginatePopular)
    }

    val onErrorShown: (PageError) -> Unit = {}

    Column {
        Text(
            text = "Voyager",
            style = MaterialTheme.typography.titleLarge.copy(textAlign = TextAlign.Center),
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
