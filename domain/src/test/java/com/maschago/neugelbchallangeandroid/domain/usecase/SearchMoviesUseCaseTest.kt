package com.maschago.neugelbchallangeandroid.domain.usecase

import androidx.paging.PagingData
import com.maschago.neugelbchallangeandroid.domain.MainRepository
import com.maschago.neugelbchallangeandroid.domain.model.Movie
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertSame
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class SearchMoviesUseCaseTest {


    private lateinit var repository: MainRepository
    private lateinit var useCase: SearchMoviesUseCase

    @Before
    fun setup() {
        repository = mockk()
        useCase = SearchMoviesUseCase(repository)
    }

    @Test
    fun `invoke should return the same Flow as repository returns`() = runTest {
        // Given
        val query = "star wars"
        val expectedFlow: Flow<PagingData<Movie>> = flowOf(PagingData.empty())

        // Mock the repository behavior to return the expected Flow
        coEvery { repository.searchMovies(query) } returns expectedFlow

        // When
        val resultFlow = useCase.invoke(query)

        // Then
        // Verify that the use case returns the same Flow that was mocked in the repository
        assertSame(expectedFlow, resultFlow)

        // Verify that the repository was called with the correct query
        verify(exactly = 1) { repository.searchMovies(query) }
    }
}
