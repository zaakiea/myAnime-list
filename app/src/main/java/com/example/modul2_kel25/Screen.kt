package com.example.modul2_kel25

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Movie
import androidx.compose.ui.graphics.vector.ImageVector

// File: Screen.kt

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Anime : Screen("anime", "Anime", Icons.Default.Movie)
    object Character : Screen("character", "Character", Icons.Default.Face)
    object About : Screen("about", "About", Icons.Default.Info)
}