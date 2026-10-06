package com.example.pokemonapp.ui.screens.about

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pokemonapp.ui.theme.DarkBackground
import com.example.pokemonapp.ui.theme.DarkCard
import com.example.pokemonapp.ui.theme.DarkSurface
import com.example.pokemonapp.ui.theme.PokemonBlueLight
import com.example.pokemonapp.ui.theme.PokemonRed
import com.example.pokemonapp.ui.theme.PokemonYellow
import com.example.pokemonapp.ui.theme.TextPrimary
import com.example.pokemonapp.ui.theme.TextSecondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Tentang Saya",
                        style = MaterialTheme.typography.titleLarge,
                        color = TextPrimary,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = PokemonYellow
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = DarkSurface,
                    titleContentColor = TextPrimary
                )
            )
        },
        containerColor = DarkBackground
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(24.dp))

            // Profile Picture Area
            Box(
                modifier = Modifier
                    .size(140.dp)
                    .clip(CircleShape)
                    .background(
                        Brush.radialGradient(
                            colors = listOf(PokemonRed.copy(alpha = 0.8f), Color(0xFF1A1A2E))
                        )
                    )
                    .border(3.dp, PokemonYellow, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "Profile",
                    tint = Color.White,
                    modifier = Modifier.size(80.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Name
            Text(
                text = "Muhammad Fathan Ramdani",
                style = MaterialTheme.typography.headlineSmall,
                color = TextPrimary,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(4.dp))

            // NIM Badge
            Box(
                modifier = Modifier
                    .background(
                        color = PokemonRed.copy(alpha = 0.2f),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .border(1.dp, PokemonRed, RoundedCornerShape(12.dp))
                    .padding(horizontal = 16.dp, vertical = 6.dp)
            ) {
                Text(
                    text = "NIM: H1D024026",
                    color = PokemonRed,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Info Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = DarkCard)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    InfoRow(label = "Program Studi", value = "Informatika")
                    Divider(color = Color(0xFF2D2D4E), modifier = Modifier.padding(vertical = 8.dp))
                    InfoRow(label = "Universitas", value = "Universitas Jenderal Soedirman")
                    Divider(color = Color(0xFF2D2D4E), modifier = Modifier.padding(vertical = 8.dp))
                    InfoRow(label = "Mata Kuliah", value = "Praktikum Pemrograman Mobile")
                    Divider(color = Color(0xFF2D2D4E), modifier = Modifier.padding(vertical = 8.dp))
                    InfoRow(label = "Shift Awal", value = "Shift G")
                    Divider(color = Color(0xFF2D2D4E), modifier = Modifier.padding(vertical = 8.dp))
                    InfoRow(label = "Shift Akhir", value = "Shift C")
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // App Info Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = DarkCard)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = "Tentang Aplikasi",
                        style = MaterialTheme.typography.titleMedium,
                        color = PokemonYellow,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "PokéDex adalah aplikasi Android yang menampilkan informasi lengkap tentang Pokémon menggunakan PokéAPI. Dibuat dengan Kotlin dan Jetpack Compose menggunakan pola arsitektur MVVM.",
                        color = TextSecondary,
                        style = MaterialTheme.typography.bodyMedium,
                        lineHeight = 22.sp
                    )
                    Spacer(modifier = Modifier.height(12.dp))

                    // Tech Stack
                    Text(
                        text = "Tech Stack:",
                        color = PokemonYellow,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    val techStack = listOf(
                        "Kotlin" to PokemonBlueLight,
                        "Jetpack Compose" to PokemonRed,
                        "Material 3" to Color(0xFF9C27B0),
                        "Retrofit" to Color(0xFF4CAF50),
                        "Coil" to Color(0xFFFF9800),
                        "MVVM" to PokemonYellow,
                        "Navigation Compose" to Color(0xFF00BCD4),
                        "Coroutines" to Color(0xFF8BC34A)
                    )

                    techStack.chunked(2).forEach { row ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            row.forEach { (tech, color) ->
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .background(
                                            color = color.copy(alpha = 0.15f),
                                            shape = RoundedCornerShape(8.dp)
                                        )
                                        .padding(horizontal = 8.dp, vertical = 6.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = tech,
                                        color = color,
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Medium,
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Data Pokémon disediakan oleh PokéAPI\nhttps://pokeapi.co",
                color = TextSecondary,
                fontSize = 12.sp,
                textAlign = TextAlign.Center,
                lineHeight = 18.sp
            )

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            color = TextSecondary,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = value,
            color = TextPrimary,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.End
        )
    }
}
