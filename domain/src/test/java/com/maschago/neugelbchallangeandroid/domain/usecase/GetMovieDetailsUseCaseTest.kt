package com.maschago.neugelbchallangeandroid.domain.usecase

import com.maschago.neugelbchallangeandroid.domain.MainRepository
import com.maschago.neugelbchallangeandroid.domain.usecase.model.MovieTestData.mockSampleMovieDetails
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class GetMovieDetailsUseCaseTest {

    private val repository: MainRepository = mockk()
    private lateinit var useCase: GetMovieDetailsUseCase

    @Before
    fun setup() {
        Dispatchers.setMain(StandardTestDispatcher())
        useCase = GetMovieDetailsUseCase(repository)
    }

    @Test
    fun `invoke should return movie details`() = runBlocking {
        // Given
        val movieId = 586353
        val mockMovieDetails = mockSampleMovieDetails
        coEvery { repository.getMovieDetails(movieId) } returns Result.success(mockMovieDetails)

        // When
        val result = useCase.invoke(movieId)

        // Then
        assertTrue(result.isSuccess)
        assertEquals(mockMovieDetails, result.getOrNull())
    }
}
