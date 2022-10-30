package com.voyager.movies.ui

import com.voyager.movies.domain.MovieItems
import com.voyager.movies.domain.QueryItems
import java.util.UUID


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

sealed class PageError(val msg: String = "") {
    val id: String = UUID.randomUUID().toString()

    object ConnectionError : PageError(msg = "Connection Error")
    data class ApiError(val message: String) : PageError(msg = message)
    data class SearchError(val query: String) : PageError(msg = "$query Error")

}
