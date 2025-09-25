package com.example.modul2_kel25

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// File: AboutScreen.kt

@Composable
fun AboutScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Our Team", style = MaterialTheme.typography.headlineLarge)
        Text("Kelompok 25 | Shift Pagi", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(24.dp))

        MemberInfoCard(
            name = "Za'im Milzam",
            nim = "A11.2022.14359",
            photoResId = R.drawable.dummy // Pastikan file zaim.png ada di res/drawable
        )

        Spacer(modifier = Modifier.height(16.dp))

        MemberInfoCard(
            name = "Rifqi Ardiansyah",
            nim = "A11.2022.14363",
            photoResId = R.drawable.dummy// Pastikan file rifqi.png ada di res/drawable
        )

        Spacer(modifier = Modifier.height(16.dp))

        // --- ANGGOTA BARU 1 ---
        MemberInfoCard(
            name = "Nama Anggota 3",
            nim = "A11.2022.XXXXX",
            photoResId = R.drawable.dummy // Ganti dengan nama file foto Anda
        )

        Spacer(modifier = Modifier.height(16.dp))

        // --- ANGGOTA BARU 2 ---
        MemberInfoCard(
            name = "Nama Anggota 4",
            nim = "A11.2022.XXXXX",
            photoResId = R.drawable.dummy // Ganti dengan nama file foto Anda
        )
    }
}

@Composable
fun MemberInfoCard(name: String, nim: String, photoResId: Int) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = photoResId),
                contentDescription = "Foto Profil $name",
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(name, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.titleLarge)
                Text(nim, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AboutScreenPreview() { AboutScreen() }