package com.maschago.neugelbchallangeandroid.presentation.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.maschago.neugelbchallangeandroid.presentation.navigation.Screen
import com.maschago.neugelbchallangeandroid.presentation.ui.components.MovieList
import com.maschago.neugelbchallangeandroid.presentation.viewmodel.MovieListViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MovieScreen(
    navController: NavController,
    viewModel: MovieListViewModel = koinViewModel(),
) {

    val movies = viewModel.moviesFlow.collectAsLazyPagingItems()

    //  Display the MovieList with paginated data
    MovieList(
        movies = movies,
        onMovieClick = {
            navController.navigate(Screen.MovieDetails.createRoute(it))
        }
    )
}
