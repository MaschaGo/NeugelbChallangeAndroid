package com.maschago.neugelbchallangeandroid.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.maschago.neugelbchallangeandroid.presentation.ui.screens.MovieDetailsScreen
import com.maschago.neugelbchallangeandroid.presentation.viewmodel.MovieDetailsViewModel
import com.maschago.neugelbchallangeandroid.presentation.viewmodel.MovieListViewModel
import com.maschago.neugelbchallangeandroid.presentation.ui.screens.MovieScreen
import com.maschago.neugelbchallangeandroid.presentation.ui.screens.SearchScreen
import com.maschago.neugelbchallangeandroid.presentation.viewmodel.SearchViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun NavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Movies.route,
        modifier = modifier
    ) {
        composable(Screen.Movies.route) {
            MovieScreen(
                navController = navController,
                viewModel = koinViewModel<MovieListViewModel>()
            )
        }

        composable(Screen.Search.route) {
            SearchScreen(
                viewModel = koinViewModel<SearchViewModel>(),
                onMovieClick = { movieId ->
                    navController.navigate(Screen.MovieDetails.createRoute(movieId))
                }
            )
        }

        composable(
            Screen.MovieDetails.route,
            arguments = listOf(
                navArgument("movieId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt("movieId")
            movieId?.let { id ->
                MovieDetailsScreen(
                    viewModel = koinViewModel<MovieDetailsViewModel>(),
                    movieId = id
                )
            }
        }
    }
}