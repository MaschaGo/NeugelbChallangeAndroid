package com.maschago.neugelbchallangeandroid.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.maschago.neugelbchallangeandroid.domain.model.Movie
import com.maschago.neugelbchallangeandroid.domain.usecase.SearchMoviesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchViewModel(private val searchMoviesUseCase: SearchMoviesUseCase) : ViewModel() {

    private val _searchResults = MutableStateFlow<PagingData<Movie>>(PagingData.empty())
    val searchResults: StateFlow<PagingData<Movie>> = _searchResults

    fun searchMovies(query: String) {
        viewModelScope.launch {
            searchMoviesUseCase(query)
                .cachedIn(viewModelScope)
                .collect {
                    _searchResults.value = it
                }
        }
    }

    // Clear the search results
    fun clearSearch() {
        _searchResults.value = PagingData.empty() // Reset the search results
    }
}
