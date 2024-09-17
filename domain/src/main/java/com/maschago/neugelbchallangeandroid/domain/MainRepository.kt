package com.maschago.neugelbchallangeandroid.domain


import androidx.paging.PagingData
import com.maschago.neugelbchallangeandroid.domain.model.Movie
import com.maschago.neugelbchallangeandroid.domain.model.MovieDetails
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    fun getMovies(): Flow<PagingData<Movie>>

    suspend fun getMovieDetails(movieId: Int): Result<MovieDetails>

    fun searchMovies(query: String): Flow<PagingData<Movie>>
}