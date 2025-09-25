package com.example.modul2_kel25.network

import com.example.modul2_kel25.model.AnimeListResponse
import com.example.modul2_kel25.model.AnimeResponse
import com.example.modul2_kel25.model.CharacterListResponse
import com.example.modul2_kel25.model.CharacterResponse
import com.example.modul2_kel25.model.GenreResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

// File: network/ApiService.kt

interface ApiService {
    // --- ANIME ENDPOINTS ---
    @GET("top/anime")
    suspend fun getTopAnime(): AnimeListResponse

    @GET("anime/{id}")
    suspend fun getAnimeById(@Path("id") id: Int): AnimeResponse

    @GET("anime")
    suspend fun searchAnime(@Query("q") query: String): AnimeListResponse

    @GET("anime")
    suspend fun getAnimeByGenre(@Query("genres") genreId: Int): AnimeListResponse

    // --- CHARACTER ENDPOINTS ---
    @GET("top/characters")
    suspend fun getTopCharacters(): CharacterListResponse

    @GET("characters/{id}")
    suspend fun getCharacterById(@Path("id") id: Int): CharacterResponse

    @GET("characters")
    suspend fun searchCharacters(@Query("q") query: String): CharacterListResponse

    // --- GENRE ENDPOINTS ---
    @GET("genres/anime")
    suspend fun getAnimeGenres(): GenreResponse
}