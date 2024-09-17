package com.maschago.neugelbchallangeandroid.data.remote

import com.maschago.neugelbchallangeandroid.data.model.MovieDetailsDto
import com.maschago.neugelbchallangeandroid.data.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("discover/movie")
    suspend fun fetchMovies(@Query("page") page: Int): Response<MovieResponse>

    @GET("movie/{movie_id}")
    suspend fun fetchMovieDetails(@Path("movie_id") id: Int): Response<MovieDetailsDto>

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("query") query: String,
        @Query("page") page: Int,
    ): Response<MovieResponse>
}