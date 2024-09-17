package com.maschago.neugelbchallangeandroid.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.maschago.neugelbchallangeandroid.data.model.toMovie
import com.maschago.neugelbchallangeandroid.domain.model.Movie
import retrofit2.HttpException
import java.io.IOException

class MovieSearchPagingSource(
    private val apiService: ApiService,
    private val query: String
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: 1
        return try {
            val response = apiService.searchMovies(query, page)
            if (response.isSuccessful) {
                val movies = response.body()?.results ?: emptyList()
                LoadResult.Page(
                    data = movies.map { it.toMovie() },
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = if (movies.isEmpty()) null else page + 1
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
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}