package com.maschago.neugelbchallangeandroid.presentation


import com.maschago.neugelbchallangeandroid.presentation.viewmodel.MovieDetailsViewModel
import com.maschago.neugelbchallangeandroid.presentation.viewmodel.MovieListViewModel
import com.maschago.neugelbchallangeandroid.presentation.viewmodel.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MovieListViewModel(getMoviesUseCase = get()) }
    viewModel { MovieDetailsViewModel(getMovieDetailsUseCase = get()) }
    viewModel { SearchViewModel(searchMoviesUseCase = get()) }
}
