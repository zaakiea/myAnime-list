package com.example.modul2_kel25.network

import com.example.modul2_kel25.model.AnimeListResponse
import retrofit2.http.GET

interface ApiService {
    // Anime by id
    @GET("top/anime")
    suspend fun getTopAnime(): AnimeListResponse
}