package com.example.movieappmad24.navigation

sealed class Screen(val route: String) {
    object MovieList : Screen("movieList")
    object MovieDetail : Screen("movieDetail/{movieId}") {
        fun withArgs(movieId: String): String {
            return route.replace("{movieId}", movieId)
        }
    }
}