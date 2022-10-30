package com.voyager.movies.ui

import com.voyager.di.UseCase
import com.voyager.movies.domain.MovieItem
import com.voyager.movies.utils.MainDispatcherRule
import com.voyager.utils.Result
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.io.IOException

class MovieViewModelTest {

    @Mock
    val popularMovieUseCase: UseCase<Result<List<MovieItem>>> = mock()

    private lateinit var viewModel: MovieViewModel

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setUp() {
        viewModel = MovieViewModel(popularMovieUseCase)
    }

    @Test
    fun loadError() = runTest {
        whenever(popularMovieUseCase.invoke()).doReturn(Result.Error(IOException()))
        viewModel.load()
        verify(popularMovieUseCase).invoke()
        assertTrue(viewModel.state.value is UiState.Error)
    }

    @Test
    fun loadSuccess() = runTest {
        val expected = listOf(
            MovieItem(
                id = 1,
                title = "Fight Club"
            )
        )
        whenever(popularMovieUseCase.invoke()).doReturn(Result.Success(expected))
        viewModel.load()
        verify(popularMovieUseCase).invoke()
        assertTrue(viewModel.state.value is UiState.Success)
        val result = (viewModel.state.value as UiState.Success).popular
        assertTrue(result[0] == expected[0])
    }
}
