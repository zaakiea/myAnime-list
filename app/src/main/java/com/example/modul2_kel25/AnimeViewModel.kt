package com.example.modul2_kel25
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.modul2_kel25.model.Anime
import com.example.modul2_kel25.model.AnimeListResponse
import com.example.modul2_kel25.network.ApiClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
class AnimeViewModel : ViewModel() {
    private val _animeList =
        MutableStateFlow<List<Anime>>(emptyList())
    val animeList: StateFlow<List<Anime>> = _animeList
    fun fetchTopAnime() {
        viewModelScope.launch {
            try {
                val response: AnimeListResponse =
                    ApiClient.service.getTopAnime()
                _animeList.value = response.data
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}