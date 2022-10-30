package com.voyager.movies.domain

import com.voyager.utils.Result


interface MovieRepository {

    suspend fun popular(page: Int): Result<MovieItems>

    suspend fun nowPlaying(): Result<MovieItems>

    suspend fun topRated(): Result<MovieItems>

    suspend fun detail(id: String): Result<MovieDetail>

    suspend fun search(query: String): Result<QueryItems>
}
