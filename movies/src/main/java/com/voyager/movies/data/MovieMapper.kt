package com.voyager.movies.data

import com.voyager.movies.data.remote.MovieDetailDto
import com.voyager.movies.data.remote.MovieItemDto
import com.voyager.movies.data.remote.MovieResponseDto
import com.voyager.movies.domain.MovieDetail
import com.voyager.movies.domain.MovieItem
import com.voyager.movies.domain.MovieItems
import com.voyager.movies.domain.QueryItems

fun MovieItemDto.toMovieItem() = MovieItem(
    id = this.id,
    title = this.title,
    "https://image.tmdb.org/t/p/w500/${this.backdropPath}", ""
)

fun MovieResponseDto.toMovieItems(): MovieItems {
    return MovieItems(page, results.map { it.toMovieItem() })
}

fun MovieResponseDto.toQueryItems(query: String): QueryItems {
    return QueryItems(query, page, results.map { it.toMovieItem() })
}

fun MovieDetailDto.toMovieDetail() = MovieDetail(1, "", "", "")
