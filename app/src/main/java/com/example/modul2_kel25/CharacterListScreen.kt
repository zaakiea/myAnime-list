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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter

// File: CharacterListScreen.kt

@Composable
fun CharacterListScreen(navController: NavController, viewModel: AnimeViewModel = viewModel()) {
    val characterList by viewModel.searchedAndSortedCharacterList.collectAsState()
    val searchText by viewModel.searchTextCharacter.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchTopCharacters()
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = searchText,
                onValueChange = viewModel::onSearchTextChangeCharacter,
                modifier = Modifier.weight(1f),
                placeholder = { Text("Search Character") }
            )
            IconButton(onClick = viewModel::onSortOrderChangeCharacter) {
                Icon(Icons.Default.SortByAlpha, contentDescription = "Sort")
            }
        }
        LazyColumn(modifier = Modifier.fillMaxSize().padding(8.dp)) {
            items(characterList) { character ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clickable { navController.navigate("character_detail/${character.malId}") }
                ) {
                    Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = rememberAsyncImagePainter(character.images.jpg.imageUrl),
                            contentDescription = character.name,
                            modifier = Modifier.size(80.dp)
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(character.name, style = MaterialTheme.typography.titleMedium)
                    }
                }
            }
        }
    }
}