package com.example.modul2_kel25

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter

@Composable
fun AnimeDetailScreen(animeId: Int, viewModel: AnimeViewModel = viewModel()) {
    // Mengambil data anime dari ViewModel
    val anime by viewModel.getAnimeById(animeId).collectAsState(initial = null)

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Tampilkan loading indicator jika data belum siap
        if (anime == null) {
            CircularProgressIndicator()
        } else {
            // Tampilkan detail jika data sudah ada
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()), // Agar bisa di-scroll
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Gambar Anime
                Image(
                    painter = rememberAsyncImagePainter(model = anime!!.images.jpg.image_url),
                    contentDescription = anime!!.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Judul
                Text(
                    text = anime!!.title,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Detail Lainnya
                Text(text = "Type: ${anime!!.type ?: "N/A"}")
                Text(text = "Episodes: ${anime!!.episodes ?: "N/A"}")
                Text(text = "Score: ${anime!!.score ?: "N/A"}")
            }
        }
    }
}