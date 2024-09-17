package com.maschago.neugelbchallangeandroid.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.maschago.neugelbchallangeandroid.presentation.ui.components.InformationView
import com.maschago.neugelbchallangeandroid.presentation.ui.components.LoadingView
import com.maschago.neugelbchallangeandroid.presentation.ui.components.MovieDetailsView
import com.maschago.neugelbchallangeandroid.presentation.viewmodel.MovieDetailsViewModel
import com.maschago.neugelbchallangeandroid.presentation.viewmodel.ScreenState
import org.koin.androidx.compose.koinViewModel

@Composable
fun MovieDetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: MovieDetailsViewModel = koinViewModel(), movieId: Int,
) {
    viewModel.getMovieItem(movieId)
    val state by viewModel.state.collectAsState()
    Column(
        modifier = modifier
            .padding(

            )
            .verticalScroll(rememberScrollState()),
    ) {
        when (state) {
            is ScreenState.Loading -> {
                LoadingView(modifier)
            }

            is ScreenState.Success -> {
                val movie = (state as ScreenState.Success).movieDetails
                MovieDetailsView(movie)
            }

            is ScreenState.Error -> {
                val errorMessage = (state as ScreenState.Error).message
                InformationView(modifier, errorMessage, onButtonClick = { viewModel.getMovieItem(movieId) })
            }
        }
    }
}