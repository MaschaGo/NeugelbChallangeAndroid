package com.maschago.neugelbchallangeandroid.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.maschago.neugelbchallangeandroid.domain.model.Movie
import com.maschago.neugelbchallangeandroid.domain.usecase.GetMoviesListUseCase
import kotlinx.coroutines.flow.Flow


class MovieListViewModel(
    getMoviesUseCase: GetMoviesListUseCase,
) : ViewModel() {

    // Return a Flow of PagingData<Movie> to the UI
    val moviesFlow: Flow<PagingData<Movie>> = getMoviesUseCase.invoke()
        .cachedIn(viewModelScope) // Cache the result to prevent reloading on configuration changes

}
