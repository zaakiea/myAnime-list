package com.example.modul2_kel25

sealed class Screen(val route: String, val title: String) {
    object Anime : Screen("anime", "Anime")
    object About : Screen("about", "About")
}

