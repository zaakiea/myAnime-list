package com.example.modul2_kel25

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.modul2_kel25.ui.theme.Modul2_Kel25Theme
import androidx.navigation.NavType // <- TAMBAHKAN IMPORT INI
import androidx.navigation.navArgument

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Modul2_Kel25Theme {
                AnimeApp()
            }
        }
    }
}
@Composable
fun AnimeApp() {
    val navController = rememberNavController()
    val items = listOf(Screen.Anime, Screen.About)
    Scaffold(
        bottomBar = {
            NavigationBar {
                val currentBackStack by
                navController.currentBackStackEntryAsState()
                val currentRoute =
                    currentBackStack?.destination?.route
                items.forEach { screen ->
                    NavigationBarItem(
                        icon = {
                            when (screen) {
                                Screen.Anime ->
                                    Icon(Icons.Default.Movie, contentDescription = "Anime")
                                Screen.About ->
                                    Icon(Icons.Default.Info, contentDescription = "About")
                            }
                        },

                        label = { Text(screen.title) },
                        selected = currentRoute == screen.route,
                        onClick = {

                            navController.navigate(screen.route)
                            {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }

                                launchSingleTop = true
                                restoreState = true

                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = Screen.Anime.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Anime.route) {
                // Teruskan navController ke AnimeListScreen
                AnimeListScreen(navController = navController)
            }
            composable(Screen.About.route) {
                AboutScreen()
            }

            // --- TAMBAHKAN COMPOSABLE BARU DI BAWAH INI ---
            composable(
                route = "anime_detail/{animeId}", // Rute dengan parameter
                arguments = listOf(navArgument("animeId") { type = NavType.IntType })
            ) { backStackEntry ->
                val animeId = backStackEntry.arguments?.getInt("animeId")
                // Pastikan animeId tidak null sebelum memanggil screen
                requireNotNull(animeId) { "Parameter animeId tidak ditemukan!" }
                AnimeDetailScreen(animeId = animeId)
            }

        }
    }
}