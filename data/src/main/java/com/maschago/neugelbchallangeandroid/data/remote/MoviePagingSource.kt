package com.maschago.neugelbchallangeandroid.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.maschago.neugelbchallangeandroid.data.model.toMovie
import com.maschago.neugelbchallangeandroid.domain.model.Movie
import retrofit2.HttpException
import java.io.IOException

private const val INITIAL_PAGE_INDEX = 1

class MoviePagingSource(
    private val apiService: ApiService,
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: INITIAL_PAGE_INDEX // Default to page 1

        return try {
            // Emit loading state
            val response = apiService.fetchMovies(page)

            if (response.isSuccessful) {
                val movies = response.body()?.results?.map { it.toMovie() } ?: emptyList()

                // Return paged data
                LoadResult.Page(
                    data = movies,
                    prevKey = if (page == 1) null else page - 1, // Previous page (null for first page)
                    nextKey = if (movies.isEmpty()) null else page + 1 // Next page (null if no more data)
                )
            } else {
                // Return an error result if the response is not successful
                when (response.code()) {
                    404 -> LoadResult.Error(Exception("Error 404: Resource not found"))
                    500 -> LoadResult.Error(Exception("Error 500: Server error"))
                    else -> {
                        val errorBody = response.errorBody()?.string() ?: "Unknown error"
                        LoadResult.Error(Exception("Error ${response.code()}: $errorBody"))
                    }
                }
            }
        } catch (e: IOException) {
            // Handle network errors
            LoadResult.Error(e)
        } catch (e: HttpException) {
            // Handle HTTP errors
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}