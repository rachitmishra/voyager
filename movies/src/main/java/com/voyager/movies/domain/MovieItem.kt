package com.voyager.movies.domain

import com.voyager.movies.ui.PageError

data class MovieItem(val id: Int, val title: String, val image: String? = null, val description: String = "")

data class MovieItems(
    val page: Int = 1,
    val movies: List<MovieItem> = listOf(),
    val loading: Boolean = false,
    val error: PageError? = null
) {

    operator fun plus(items: MovieItems): MovieItems {
        return MovieItems(page = items.page, movies = movies + items.movies)
    }
}

data class QueryItems(
    val query: String = "",
    val page: Int = 1,
    val movies: List<MovieItem> = listOf(),
    val loading: Boolean = false,
    val error: PageError? = null
)
