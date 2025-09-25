package com.example.modul2_kel25.model

import com.google.gson.annotations.SerializedName

// File: model/GenreResponse.kt

data class GenreResponse(
    val data: List<Genre>
)

data class Genre(
    @SerializedName("mal_id")
    val malId: Int,
    val name: String
)