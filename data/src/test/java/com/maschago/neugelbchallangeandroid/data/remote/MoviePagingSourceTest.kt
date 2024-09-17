package com.maschago.neugelbchallangeandroid.data.remote

import com.maschago.neugelbchallangeandroid.data.model.toMovie
import com.maschago.neugelbchallangeandroid.data.utils.enqueueResponse
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MoviePagingSourceTest {
    private lateinit var mockWebServer: MockWebServer
    private lateinit var okHttpClient: OkHttpClient
    private lateinit var apiService: ApiService

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        okHttpClient = OkHttpClient()
            .newBuilder()
            .build()

        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @After
    fun teardown() {
        // Shut down the server after tests
        mockWebServer.shutdown()
    }

    @Test
    fun `getMovieDetails should return failure when API call fails`() = runTest {
        val movieId = 1
        val mockResponse = MockResponse().setResponseCode(404)

        mockWebServer.enqueue(mockResponse)

        runBlocking {
            val actual = apiService.fetchMovieDetails(movieId)
            assertTrue(!actual.isSuccessful)
        }
    }

    @Test
    fun `should fetch Movies correctly given 200 response`() = runTest {
        mockWebServer.enqueueResponse("movies-list-response.json", 200)

        runBlocking {
            val actual = apiService.fetchMovies(1)
            assertNotNull(actual)
        }
    }

    @Test
    fun `getMovies should return PagingData when API call is successful`() = runTest {
        mockWebServer.enqueueResponse("movies-list-response.json", 200)

        runBlocking {
            val actual = apiService.fetchMovies(1).body()?.results?.map { it.toMovie() }?.get(0)?.id
            assertEquals(13475, actual)
        }
    }

}
