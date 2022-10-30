package com.voyager.movies.data

import com.voyager.movies.data.remote.MovieApi
import com.voyager.movies.data.remote.MovieItemDto
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

class MovieRepositoryImplTest {

    @Mock
    val remoteSource: MovieApi = mock()

    private lateinit var repository: MovieRepositoryImpl

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setUp() {
        repository = MovieRepositoryImpl(remoteSource)
    }

    @Test
    fun getPopular() = runTest {
        whenever(remoteSource.popular()).doReturn(listOf(MovieItemDto(id = 550)))
        val result = repository.popular()
        verify(remoteSource).popular()
        assertTrue(result is Result.Success)
        val resultFirstItem = (result as Result.Success).data?.get(0)
        val expected = MovieItem(id = 550, title = "", image = "")
        assertTrue(resultFirstItem == expected)
    }
}
