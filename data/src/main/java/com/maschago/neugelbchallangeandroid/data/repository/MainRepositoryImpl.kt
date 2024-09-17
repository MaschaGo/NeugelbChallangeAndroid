package com.maschago.neugelbchallangeandroid.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.maschago.neugelbchallangeandroid.data.model.toMovieDetails
import com.maschago.neugelbchallangeandroid.data.remote.ApiService
import com.maschago.neugelbchallangeandroid.data.remote.MoviePagingSource
import com.maschago.neugelbchallangeandroid.data.remote.MovieSearchPagingSource
import com.maschago.neugelbchallangeandroid.domain.MainRepository
import com.maschago.neugelbchallangeandroid.domain.model.Movie
import com.maschago.neugelbchallangeandroid.domain.model.MovieDetails
import kotlinx.coroutines.flow.Flow

class MainRepositoryImpl(private val apiService: ApiService) : MainRepository {

    override suspend fun getMovieDetails(movieId: Int): Result<MovieDetails> =
        runCatching {
            apiService.fetchMovieDetails(movieId)
        }.map { response ->
            if (response.isSuccessful) {
                return response.body()?.let { movieDetails ->
                    Result.success(
                        movieDetails.toMovieDetails()
                    )
                } ?: throw Exception("Empty response")
            } else {
                return response.errorBody()
                    ?.let { Result.failure(Throwable(message = it.string())) }
                    ?: throw Exception("Unknown error")

            }
        }

    // Return PagingData instead of a List<Movie>
    override fun getMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20, // Define the number of items per page
                enablePlaceholders = false
            ),
            pagingSourceFactory = { MoviePagingSource(apiService) }
        ).flow
    }

    override fun searchMovies(query: String): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = {
                MovieSearchPagingSource(apiService, query)
            }
        ).flow
    }
}