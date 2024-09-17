package com.maschago.neugelbchallangeandroid.domain.usecase

import com.maschago.neugelbchallangeandroid.domain.MainRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetMovieDetailsUseCase(
    private val repo: MainRepository, private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) {
    suspend operator fun invoke(itemId: Int) = withContext(dispatcher) {
        repo.getMovieDetails(itemId)
    }
}
