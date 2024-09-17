package com.maschago.neugelbchallangeandroid.data.repository

import androidx.paging.PagingSource
import com.maschago.neugelbchallangeandroid.data.model.toMovie
import com.maschago.neugelbchallangeandroid.data.remote.ApiService
import com.maschago.neugelbchallangeandroid.data.remote.MoviePagingSource
import com.maschago.neugelbchallangeandroid.data.utils.MovieTestData.sampleMovieDetails
import com.maschago.neugelbchallangeandroid.data.utils.MovieTestData.sampleMoviesList
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MainRepositoryImplTest {
    private val apiService = mock<ApiService>()

    private lateinit var repositoryImpl: MainRepositoryImpl

    @Before
    fun setUp() {
        repositoryImpl = MainRepositoryImpl(apiService)
    }

    @Test
    fun `getMovies should return a paging data`() {
        runBlocking {
            val pagingSource = MoviePagingSource(apiService)

            whenever(apiService.fetchMovies(1)) doReturn sampleMoviesList

            val actual = pagingSource.load(
                PagingSource.LoadParams.Refresh(
                    key = null,
                    loadSize = 1,
                    placeholdersEnabled = false
                )
            ) as PagingSource.LoadResult.Page

            val expected = sampleMoviesList.body()?.let {
                PagingSource.LoadResult.Page(
                    data = it.results.map { movie -> movie.toMovie() },
                    prevKey = null,
                    nextKey = 1
                )
            }

            Assert.assertEquals(
                expected!!.data,
                actual.data
            )
        }
    }

    @Test
    fun `fetchMovieDetails should return expected movie details`() {
        runBlocking {
            whenever(apiService.fetchMovieDetails(586353)) doReturn sampleMovieDetails

            val actual = repositoryImpl.getMovieDetails(586353)

            Assert.assertEquals(
                actual.fold(onSuccess = { it.budget }, onFailure = {}),
                15000000
            )
        }
    }

    @Test
    fun `fetchMovies should call it once`() {
        runBlocking {
            apiService.fetchMovies(1)

            verify(apiService, times(1)).fetchMovies(1)
        }
    }

    @Test
    fun `fetchMovieDetails should call it once`() {
        runBlocking {
            apiService.fetchMovieDetails(1)

            verify(apiService, times(1)).fetchMovieDetails(1)
        }
    }

}