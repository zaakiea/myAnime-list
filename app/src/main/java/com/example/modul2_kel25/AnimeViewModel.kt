package com.example.modul2_kel25

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.modul2_kel25.model.Anime
import com.example.modul2_kel25.model.Character
import com.example.modul2_kel25.model.Genre
import com.example.modul2_kel25.network.ApiClient
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

// File: AnimeViewModel.kt

class AnimeViewModel : ViewModel() {

    // --- State for Anime ---
    private val _animeList = MutableStateFlow<List<Anime>>(emptyList())
    private val _searchTextAnime = MutableStateFlow("")
    val searchTextAnime: StateFlow<String> = _searchTextAnime
    private val _sortAscendingAnime = MutableStateFlow(true)
    val sortAscendingAnime: StateFlow<Boolean> = _sortAscendingAnime
    private val _genres = MutableStateFlow<List<Genre>>(emptyList())
    val genres: StateFlow<List<Genre>> = _genres
    private val _selectedGenre = MutableStateFlow<Genre?>(null)
    val selectedGenre: StateFlow<Genre?> = _selectedGenre

    val searchedAndSortedAnimeList: StateFlow<List<Anime>> =
        combine(_animeList, _searchTextAnime, _sortAscendingAnime) { list, text, ascending ->
            val searchedList = if (text.isBlank()) list else list.filter { it.title.contains(text, ignoreCase = true) }
            if (ascending) searchedList.sortedBy { it.title } else searchedList.sortedByDescending { it.title }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // --- State for Character ---
    private val _characterList = MutableStateFlow<List<Character>>(emptyList())
    private val _searchTextCharacter = MutableStateFlow("")
    val searchTextCharacter: StateFlow<String> = _searchTextCharacter
    private val _sortAscendingCharacter = MutableStateFlow(true)
    val sortAscendingCharacter: StateFlow<Boolean> = _sortAscendingCharacter

    val searchedAndSortedCharacterList: StateFlow<List<Character>> =
        combine(_characterList, _searchTextCharacter, _sortAscendingCharacter) { list, text, ascending ->
            val searchedList = if (text.isBlank()) list else list.filter { it.name.contains(text, ignoreCase = true) }
            if (ascending) searchedList.sortedBy { it.name } else searchedList.sortedByDescending { it.name }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())


    // --- Functions for Anime ---
    fun onSearchTextChangeAnime(text: String) { _searchTextAnime.value = text }
    fun onSortOrderChangeAnime() { _sortAscendingAnime.value = !_sortAscendingAnime.value }
    fun onGenreSelected(genre: Genre?) {
        _selectedGenre.value = genre
        fetchTopAnime(genre?.malId)
    }

    fun fetchTopAnime(genreId: Int? = null) {
        viewModelScope.launch {
            try {
                val response = if (genreId != null) ApiClient.service.getAnimeByGenre(genreId) else ApiClient.service.getTopAnime()
                _animeList.value = response.data
            } catch (e: Exception) { e.printStackTrace() }
        }
    }

    fun getAnimeById(id: Int) = flow {
        try {
            emit(ApiClient.service.getAnimeById(id).data)
        } catch (e: Exception) { e.printStackTrace() }
    }

    fun fetchAnimeGenres() {
        viewModelScope.launch {
            try {
                _genres.value = ApiClient.service.getAnimeGenres().data
            } catch (e: Exception) { e.printStackTrace() }
        }
    }

    // --- Functions for Character ---
    fun onSearchTextChangeCharacter(text: String) { _searchTextCharacter.value = text }
    fun onSortOrderChangeCharacter() { _sortAscendingCharacter.value = !_sortAscendingCharacter.value }

    fun fetchTopCharacters() {
        viewModelScope.launch {
            try {
                _characterList.value = ApiClient.service.getTopCharacters().data
            } catch (e: Exception) { e.printStackTrace() }
        }
    }

    fun getCharacterById(id: Int) = flow {
        try {
            emit(ApiClient.service.getCharacterById(id).data)
        } catch (e: Exception) { e.printStackTrace() }
    }
}