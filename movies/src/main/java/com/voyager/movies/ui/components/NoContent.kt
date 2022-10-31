package com.voyager.movies.ui.components

import androidx.compose.runtime.Composable
import com.voyager.movies.ui.state.MoviePage
import com.voyager.movies.ui.state.PageError

@Composable
fun NoContent(
    state: MoviePage.NoContent, onErrorShown: (error: PageError) -> Unit
) {
    if (state.loading) {
        ProgressBox()
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
