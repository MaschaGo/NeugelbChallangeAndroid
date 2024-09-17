package com.maschago.neugelbchallangeandroid.domain.usecase

import androidx.paging.PagingData
import com.maschago.neugelbchallangeandroid.domain.MainRepository
import com.maschago.neugelbchallangeandroid.domain.model.Movie
import com.maschago.neugelbchallangeandroid.domain.usecase.model.MovieTestData.mockMoviesList
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetMoviesListUseCaseTest {

    private val repository: MainRepository = mockk()
    private lateinit var useCase: GetMoviesListUseCase

    @Before
    fun setup() {
        Dispatchers.setMain(StandardTestDispatcher()) // Set test dispatcher
        useCase = GetMoviesListUseCase(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // Reset the main dispatcher to the original one after the test
    }

    @Test
    fun `invoke should return PagingData of movies`() = runTest {
        // Given
        val mockMoviesPagingData = PagingData.from(mockMoviesList)
        coEvery { repository.getMovies() } returns flowOf(mockMoviesPagingData)

        // When
        val resultFlow = useCase()

        // Then
        resultFlow.collect { pagingData ->
            assertTrue(pagingData is PagingData<Movie>)
        }
    }

}


