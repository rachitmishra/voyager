package com.voyager.movies.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("discover/movie?sort_by=popularity.desc")
    suspend fun popular(@Query("page") page: Int): MovieResponseDto

    @GET("movie/now_playing")
    suspend fun nowPlaying(): MovieResponseDto

    @GET("movie/top_rated")
    suspend fun topRated(): MovieResponseDto

    @GET("movie")
    suspend fun detail(@Query("movie_id") id: String): MovieDetailDto

    @GET("search/movie")
    suspend fun search(@Query("query") query: String): MovieResponseDto
}
