package com.voyager.movies.data

import com.voyager.movies.data.remote.MovieApi
import com.voyager.movies.domain.MovieDetail
import com.voyager.movies.domain.MovieItems
import com.voyager.movies.domain.MovieRepository
import com.voyager.movies.domain.QueryItems
import com.voyager.utils.Result

class MovieRepositoryImpl(private val api: MovieApi) : MovieRepository {

    override suspend fun popular(page: Int): Result<MovieItems> {
        return try {
            val response = api.popular(page)
            Result.Success(response.toMovieItems())
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun nowPlaying(): Result<MovieItems> {
        return try {
            val response = api.nowPlaying()
            Result.Success(response.toMovieItems())
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun topRated(): Result<MovieItems> {
        return try {
            val response = api.topRated()
            Result.Success(response.toMovieItems())
        } catch (e: Exception) {
            Result.Error(e)
        }
    }


    override suspend fun detail(id: String): Result<MovieDetail> {
        return try {
            val response = api.detail(id)
            Result.Success(data = response.toMovieDetail())
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun search(query: String): Result<QueryItems> {
        return try {
            val response = api.search(query)
            Result.Success(response.toQueryItems(query))
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}
