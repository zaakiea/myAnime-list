package com.example.modul2_kel25

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SortByAlpha
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.modul2_kel25.model.Genre

// File: AnimeListScreen.kt

@Composable
fun AnimeListScreen(navController: NavController, viewModel: AnimeViewModel = viewModel()) {
    val animeList by viewModel.searchedAndSortedAnimeList.collectAsState()
    val searchText by viewModel.searchTextAnime.collectAsState()
    val genres by viewModel.genres.collectAsState()
    val selectedGenre by viewModel.selectedGenre.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchTopAnime()
        viewModel.fetchAnimeGenres()
    }

    Column(modifier = Modifier.fillMaxSize()) {
        OutlinedTextField(
            value = searchText,
            onValueChange = viewModel::onSearchTextChangeAnime,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp),
            placeholder = { Text("Search Anime") }
        )
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(modifier = Modifier.weight(1f)) {
                GenreDropDown(
                    genres = genres,
                    selectedGenre = selectedGenre,
                    onGenreSelected = viewModel::onGenreSelected
                )
            }
            IconButton(onClick = viewModel::onSortOrderChangeAnime) {
                Icon(Icons.Default.SortByAlpha, contentDescription = "Sort")
            }
        }
        LazyColumn(modifier = Modifier.fillMaxSize().padding(8.dp)) {
            items(animeList) { anime ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clickable { navController.navigate("anime_detail/${anime.mal_id}") }
                ) {
                    Row(modifier = Modifier.padding(16.dp)) {
                        Image(
                            painter = rememberAsyncImagePainter(anime.images.jpg.image_url),
                            contentDescription = anime.title,
                            modifier = Modifier.size(80.dp)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Column {
                            Text(anime.title, style = MaterialTheme.typography.titleMedium)
                            Text("Type: ${anime.type ?: "-"}", style = MaterialTheme.typography.bodySmall)
                            Text("Episodes: ${anime.episodes ?: 0}", style = MaterialTheme.typography.bodySmall)
                            Text("Score: ${anime.score ?: "N/A"}", style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenreDropDown(genres: List<Genre>, selectedGenre: Genre?, onGenreSelected: (Genre?) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        OutlinedTextField(
            value = selectedGenre?.name ?: "All Genres",
            onValueChange = {},
            readOnly = true,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier.menuAnchor().fillMaxWidth()
        )
        ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            DropdownMenuItem(text = { Text("All Genres") }, onClick = { onGenreSelected(null); expanded = false })
            genres.forEach { genre ->
                DropdownMenuItem(text = { Text(genre.name) }, onClick = { onGenreSelected(genre); expanded = false })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AnimeListScreenPreview() { AboutScreen() }