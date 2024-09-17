package com.maschago.neugelbchallangeandroid.presentation.navigation


sealed class Screen(val route: String) {
    data object Movies : Screen("movie_list")
    data object Search : Screen("search")
    data object MovieDetails : Screen("movie_details/{movieId}") {
        fun createRoute(movieId: Int) = "movie_details/$movieId"
    }
}
