package com.voyager.movies.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.voyager.movies.domain.MovieItems
import com.voyager.movies.domain.QueryItems
import com.voyager.movies.domain.usecase.NowPlayingUseCase
import com.voyager.movies.domain.usecase.PopularMovieUseCase
import com.voyager.movies.domain.usecase.SearchMovieUseCase
import com.voyager.movies.domain.usecase.TopRatedUseCase
import com.voyager.movies.utils.ConnectionDelegate
import com.voyager.utils.Result
import kotlinx.coroutines.launch


sealed interface Action {
    object GetPopular : Action
    object GetNowPlaying : Action
    object GetTopRated : Action
    object PaginatePopular : Action
    data class Find(val query: String) : Action
}

class MovieViewModel(
    private val popularMovies: PopularMovieUseCase,
    private val searchMovies: SearchMovieUseCase,
    private val nowPlaying: NowPlayingUseCase,
    private val topRated: TopRatedUseCase,
    private val connectionDelegate: ConnectionDelegate
) : ViewModel() {

    var state = mutableStateOf<MoviePage>(MoviePage.NoContent(loading = true))
        private set

    fun perform(action: Action) {
        when (action) {
            Action.GetPopular -> getPopularMovies()
            is Action.Find -> search(action.query)
            Action.PaginatePopular -> getPopularMovies((state.value as MoviePage.Content).popular.page)
            Action.GetNowPlaying -> getNowPlaying()
            Action.GetTopRated -> getTopRated()
        }
    }

    private fun checkConnection() {
        if (connectionDelegate.isConnected().not()) {
            state.value = MoviePage.NoContent(error = PageError.ConnectionError)
            return
        }
    }

    private fun getPopularMovies(page: Int = 1) {

        checkConnection()

        if (state.value is MoviePage.Content) {
            val prevState = (state.value as MoviePage.Content)
            val prev = prevState.popular
            state.value = prevState.copy(popular = prev.copy(loading = true))
        } else {
            state.value = MoviePage.NoContent(loading = true)
        }

        viewModelScope.launch {
            when (val result = popularMovies(page)) {
                is Result.Error -> onErrorPopular(result)
                is Result.Success -> onSuccessPopular(result)
            }
        }
    }

    private fun getNowPlaying(page: Int = 1) {

        checkConnection()

        if (state.value is MoviePage.Content) {
            val prevState = (state.value as MoviePage.Content)
            val prev = prevState.nowPlaying
            state.value = prevState.copy(nowPlaying = prev.copy(loading = true))
        } else {
            state.value = MoviePage.NoContent(loading = true)
        }

        viewModelScope.launch {
            when (val result = nowPlaying(page)) {
                is Result.Error -> onErrorNowPlaying(result)
                is Result.Success -> onSuccessNowPlaying(result)
            }
        }
    }

    private fun getTopRated(page: Int = 1) {

        checkConnection()

        if (state.value is MoviePage.Content) {
            val prevState = (state.value as MoviePage.Content)
            val prev = prevState.topRated
            state.value = prevState.copy(topRated = prev.copy(loading = true))
        } else {
            state.value = MoviePage.NoContent(loading = true)
        }

        viewModelScope.launch {
            when (val result = topRated(page)) {
                is Result.Error -> onErrorTopRated(result)
                is Result.Success -> onSuccessTopRated(result)
            }
        }
    }

    private fun onErrorPopular(result: Result.Error<MovieItems>) {
        state.value = if (state.value is MoviePage.Content) {
            val prevState = (state.value as MoviePage.Content)
            prevState.copy(popular = prevState.popular.copy(error = PageError.ApiError(result.msg)))
        } else {
            MoviePage.NoContent(error = PageError.ApiError(result.msg))
        }
    }

    private fun onErrorNowPlaying(result: Result.Error<MovieItems>) {
        state.value = if (state.value is MoviePage.Content) {
            val prevState = (state.value as MoviePage.Content)
            prevState.copy(nowPlaying = prevState.nowPlaying.copy(error = PageError.ApiError(result.msg)))
        } else {
            MoviePage.NoContent(error = PageError.ApiError(result.msg))
        }
    }

    private fun onErrorTopRated(result: Result.Error<MovieItems>) {
        state.value = if (state.value is MoviePage.Content) {
            val prevState = (state.value as MoviePage.Content)
            prevState.copy(topRated = prevState.topRated.copy(error = PageError.ApiError(result.msg)))
        } else {
            MoviePage.NoContent(error = PageError.ApiError(result.msg))
        }
    }

    private fun onSuccessPopular(result: Result.Success<MovieItems>) {
        state.value = if (state.value is MoviePage.Content) {
            val prevState = (state.value as MoviePage.Content)
            prevState.copy(
                popular = prevState.popular + result.data
            )
        } else {
            MoviePage.Content(popular = result.data)
        }

    }

    private fun onSuccessNowPlaying(result: Result.Success<MovieItems>) {
        state.value = if (state.value is MoviePage.Content) {
            val prevState = (state.value as MoviePage.Content)
            prevState.copy(
                nowPlaying = prevState.nowPlaying + result.data
            )
        } else {
            MoviePage.Content(nowPlaying = result.data)
        }

    }

    private fun onSuccessTopRated(result: Result.Success<MovieItems>) {
        state.value = if (state.value is MoviePage.Content) {
            val prevState = (state.value as MoviePage.Content)
            prevState.copy(
                topRated = prevState.topRated + result.data
            )
        } else {
            MoviePage.Content(topRated = result.data)
        }

    }

    private fun search(query: String) {
        if (connectionDelegate.isConnected().not()) {
            val prevState = (state.value as MoviePage.Content)
            val prevSearch = prevState.search
            state.value = prevState.copy(search = prevSearch.copy(error = PageError.ConnectionError))
            return
        }

        state.value = (state.value as MoviePage.Content).copy(search = QueryItems(query = query, loading = true))

        viewModelScope.launch {
            when (val result = searchMovies(query)) {
                is Result.Error -> onSearchError(query)
                is Result.Success -> onSearchSuccess(result)
            }
        }
    }

    private fun onSearchError(query: String) {
        val prevState = (state.value as MoviePage.Content)
        val prevSearch = prevState.search
        state.value = prevState.copy(search = prevSearch.copy(error = PageError.SearchError(query)))
    }

    private fun onSearchSuccess(result: Result.Success<QueryItems>) {
        val prevState = (state.value as MoviePage.Content)
        state.value = prevState.copy(
            search = result.data
        )
    }
}
