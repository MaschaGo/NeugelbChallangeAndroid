package com.maschago.neugelbchallangeandroid.domain.usecase

import androidx.paging.PagingData
import com.maschago.neugelbchallangeandroid.domain.MainRepository
import com.maschago.neugelbchallangeandroid.domain.model.Movie
import kotlinx.coroutines.flow.Flow

class SearchMoviesUseCase(private val repo: MainRepository) {

    operator fun invoke(query: String): Flow<PagingData<Movie>> {
        return repo.searchMovies(query)
    }
}