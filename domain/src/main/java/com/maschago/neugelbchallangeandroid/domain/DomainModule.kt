package com.maschago.neugelbchallangeandroid.domain

import com.maschago.neugelbchallangeandroid.domain.usecase.GetMovieDetailsUseCase
import com.maschago.neugelbchallangeandroid.domain.usecase.GetMoviesListUseCase
import com.maschago.neugelbchallangeandroid.domain.usecase.SearchMoviesUseCase
import org.koin.dsl.module

val domainModule = module {

    factory { GetMoviesListUseCase(repo = get()) }
    factory { GetMovieDetailsUseCase(repo = get()) }
    factory { SearchMoviesUseCase(repo = get()) }
}