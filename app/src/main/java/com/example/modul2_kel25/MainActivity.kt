package com.example.modul2_kel25

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.modul2_kel25.ui.theme.Modul2_Kel25Theme

// File: MainActivity.kt

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
    val items = listOf(Screen.Anime, Screen.Character, Screen.About)

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                items.forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(screen.icon, contentDescription = screen.title) },
                        label = { Text(screen.title) },
                        selected = currentRoute == screen.route,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) { saveState = true }
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
            // --- ANIME SCREENS ---
            composable(Screen.Anime.route) { AnimeListScreen(navController = navController) }
            composable(
                route = "anime_detail/{animeId}",
                arguments = listOf(navArgument("animeId") { type = NavType.IntType })
            ) { backStackEntry ->
                val animeId = requireNotNull(backStackEntry.arguments?.getInt("animeId"))
                AnimeDetailScreen(animeId = animeId)
            }

            // --- CHARACTER SCREENS ---
            composable(Screen.Character.route) { CharacterListScreen(navController = navController) }
            composable(
                route = "character_detail/{characterId}",
                arguments = listOf(navArgument("characterId") { type = NavType.IntType })
            ) { backStackEntry ->
                val characterId = requireNotNull(backStackEntry.arguments?.getInt("characterId"))
                CharacterDetailScreen(characterId = characterId)
            }

            // --- ABOUT SCREEN ---
            composable(Screen.About.route) { AboutScreen() }
        }
    }
}