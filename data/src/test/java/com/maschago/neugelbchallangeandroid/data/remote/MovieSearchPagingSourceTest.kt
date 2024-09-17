package com.maschago.neugelbchallangeandroid.data.remote

import com.maschago.neugelbchallangeandroid.data.utils.enqueueResponse
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieSearchPagingSourceTest {
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
    fun `searchMovies should return PagingData when API call is successful`() = runTest {

        val query = "star wars"
        mockWebServer.enqueueResponse("movie-search-response.json", 200)

        runBlocking {
            val actual = apiService.searchMovies(query, 1)
            assertNotNull(actual)
            assertTrue(actual.isSuccessful)
            assertNotNull(actual.body()?.results)
        }
    }

}

