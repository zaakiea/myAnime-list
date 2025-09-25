package com.example.modul2_kel25.network

import com.example.modul2_kel25.model.AnimeListResponse
import com.example.modul2_kel25.model.AnimeResponse // <- TAMBAHKAN IMPORT INI
import retrofit2.http.GET
import retrofit2.http.Path // <- TAMBAHKAN IMPORT INI

interface ApiService {
    @GET("top/anime")
    suspend fun getTopAnime(): AnimeListResponse

    // --- TAMBAHKAN FUNGSI DI BAWAH INI ---
    @GET("anime/{id}")
    suspend fun getAnimeById(@Path("id") id: Int): AnimeResponse
    // ------------------------------------
}