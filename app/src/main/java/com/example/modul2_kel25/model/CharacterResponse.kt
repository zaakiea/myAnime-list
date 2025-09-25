package com.example.modul2_kel25.model

import com.google.gson.annotations.SerializedName

// File: model/CharacterResponse.kt

data class CharacterListResponse(
    val data: List<Character>
)

data class CharacterResponse(
    val data: Character
)

data class Character(
    @SerializedName("mal_id")
    val malId: Int,
    val name: String,
    val about: String?,
    val images: CharacterImages
)

data class CharacterImages(
    val jpg: JpgImage
)

// Menggunakan nama JpgImage agar tidak konflik dengan data class Jpg di AnimeResponse
data class JpgImage(
    @SerializedName("image_url")
    val imageUrl: String
)