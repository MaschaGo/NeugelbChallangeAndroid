package com.maschago.neugelbchallangeandroid.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maschago.neugelbchallangeandroid.domain.model.MovieDetails
import com.maschago.neugelbchallangeandroid.domain.usecase.GetMovieDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


sealed class ScreenState {
    data object Loading : ScreenState()
    data class Success(val movieDetails: MovieDetails) : ScreenState()
    data class Error(val message: String) : ScreenState()
}
class MovieDetailsViewModel(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
) : ViewModel() {


    private val _state = MutableStateFlow<ScreenState>(ScreenState.Loading)
    val state: StateFlow<ScreenState> = _state

    fun getMovieItem(id: Int) {

        viewModelScope.launch {
            val result = getMovieDetailsUseCase(id)
            result.fold(
                onSuccess = { movie ->
                    _state.value = ScreenState.Success(movie)

                },
                onFailure = { error ->
                    // Show error message with specific details
                    _state.value = ScreenState.Error("Failed to load movies: ${error.localizedMessage}")
                }
            )
        }
    }



}

