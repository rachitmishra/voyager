package com.voyager.movies.di

import com.voyager.di.ApiFactory
import com.voyager.movies.data.MovieRepositoryImpl
import com.voyager.movies.data.remote.MovieApi
import com.voyager.movies.domain.usecase.NowPlayingUseCase
import com.voyager.movies.domain.usecase.PopularMovieUseCase
import com.voyager.movies.domain.usecase.SearchMovieUseCase
import com.voyager.movies.domain.usecase.TopRatedUseCase
import com.voyager.movies.ui.MovieViewModel
import com.voyager.movies.utils.ConnectionDelegate

class MovieModuleFactory(private val apiFactory: ApiFactory) {

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
    }

    fun viewModel() = MovieViewModel(
        PopularMovieUseCase(
            MovieRepositoryImpl(apiFactory.create(MovieApi::class.java))
        ),
        SearchMovieUseCase(
            MovieRepositoryImpl(apiFactory.create(MovieApi::class.java))
        ),
        NowPlayingUseCase(
            MovieRepositoryImpl(apiFactory.create(MovieApi::class.java))
        ),
        TopRatedUseCase(
            MovieRepositoryImpl(apiFactory.create(MovieApi::class.java))
        ),
        object : ConnectionDelegate {
            override fun isConnected(): Boolean {
                return true
            }
        }
    )
}
