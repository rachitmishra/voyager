package com.voyager.movies.ui.state

import com.voyager.movies.domain.MovieItems
import com.voyager.movies.domain.QueryItems

sealed interface MoviePage {
    val loading: Boolean
    val error: PageError?

    data class NoContent(
        override val loading: Boolean = false,
        override val error: PageError? = null,
    ) : MoviePage

    data class Content(
        val popular: MovieItems = MovieItems(),
        val nowPlaying: MovieItems = MovieItems(),
        val topRated: MovieItems = MovieItems(),
        val search: QueryItems = QueryItems(),
        override val loading: Boolean = false,
        override val error: PageError? = null,
    ) : MoviePage
}
