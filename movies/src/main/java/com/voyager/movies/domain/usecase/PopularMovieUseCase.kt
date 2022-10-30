package com.voyager.movies.domain.usecase

import com.voyager.movies.domain.MovieItems
import com.voyager.movies.domain.MovieRepository
import com.voyager.movies.domain.QueryItems
import com.voyager.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PopularMovieUseCase(private val repo: MovieRepository) {

    suspend operator fun invoke(page: Int = 1): Result<MovieItems> {
        return withContext(Dispatchers.IO) {
            repo.popular(page)
        }
    }
}

class NowPlayingUseCase(private val repo: MovieRepository) {

    suspend operator fun invoke(page: Int = 1): Result<MovieItems> {
        return withContext(Dispatchers.IO) {
            repo.nowPlaying()
        }
    }
}

class TopRatedUseCase(private val repo: MovieRepository) {

    suspend operator fun invoke(page: Int = 1): Result<MovieItems> {
        return withContext(Dispatchers.IO) {
            repo.topRated()
        }
    }
}

class SearchMovieUseCase(private val repo: MovieRepository) {

    suspend operator fun invoke(query: String): Result<QueryItems> {
        return withContext(Dispatchers.IO) {
            repo.search(query)
        }
    }
}

