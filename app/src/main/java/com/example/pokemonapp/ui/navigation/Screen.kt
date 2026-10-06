package com.example.pokemonapp.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Detail : Screen("detail/{pokemonName}") {
        fun createRoute(pokemonName: String) = "detail/$pokemonName"
    }
    object About : Screen("about")
}
