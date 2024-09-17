package com.maschago.neugelbchallangeandroid.domain.usecase

import androidx.paging.PagingData
import com.maschago.neugelbchallangeandroid.domain.MainRepository
import com.maschago.neugelbchallangeandroid.domain.model.Movie
import kotlinx.coroutines.flow.Flow

class GetMoviesListUseCase(
    private val repo: MainRepository,
) {
    operator fun invoke(): Flow<PagingData<Movie>> {
        return repo.getMovies()
    }
}