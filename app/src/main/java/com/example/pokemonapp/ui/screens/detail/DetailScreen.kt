package com.example.pokemonapp.ui.screens.detail

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.pokemonapp.data.model.PokemonDetail
import com.example.pokemonapp.data.model.PokemonStat
import com.example.pokemonapp.ui.theme.DarkBackground
import com.example.pokemonapp.ui.theme.DarkCard
import com.example.pokemonapp.ui.theme.DarkSurface
import com.example.pokemonapp.ui.theme.PokemonRed
import com.example.pokemonapp.ui.theme.PokemonYellow
import com.example.pokemonapp.ui.theme.TextPrimary
import com.example.pokemonapp.ui.theme.TextSecondary
import com.example.pokemonapp.ui.theme.getPokemonTypeColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    pokemonName: String,
    onBackClick: () -> Unit,
    viewModel: DetailViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(pokemonName) {
        viewModel.loadPokemonDetail(pokemonName)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = uiState.pokemon?.name?.replaceFirstChar { it.uppercase() } ?: pokemonName.replaceFirstChar { it.uppercase() },
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
        when {
            uiState.isLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        CircularProgressIndicator(
                            color = PokemonRed,
                            modifier = Modifier.size(60.dp),
                            strokeWidth = 4.dp
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            "Memuat detail...",
                            color = TextSecondary,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }

            uiState.error != null -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Error: ${uiState.error}",
                        color = PokemonRed,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }

            uiState.pokemon != null -> {
                PokemonDetailContent(
                    pokemon = uiState.pokemon!!,
                    modifier = Modifier.padding(paddingValues)
                )
            }
        }
    }
}

@Composable
fun PokemonDetailContent(
    pokemon: PokemonDetail,
    modifier: Modifier = Modifier
) {
    val primaryTypeColor = pokemon.types.firstOrNull()?.type?.name?.let {
        getPokemonTypeColor(it)
    } ?: PokemonRed

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        // Hero Section
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            primaryTypeColor.copy(alpha = 0.4f),
                            DarkBackground
                        )
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                // Pokemon Number
                Text(
                    text = "#${pokemon.id.toString().padStart(3, '0')}",
                    color = primaryTypeColor,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Pokemon Image
                AsyncImage(
                    model = pokemon.imageUrl,
                    contentDescription = pokemon.name,
                    modifier = Modifier.size(200.dp),
                    contentScale = ContentScale.Fit
                )
            }
        }

        // Types Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            pokemon.types.forEach { typeSlot ->
                TypeChip(typeName = typeSlot.type.name)
                Spacer(modifier = Modifier.width(8.dp))
            }
        }

        // Info Cards Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Height
            InfoCard(
                title = "Tinggi",
                value = "${pokemon.heightInMeters} m",
                modifier = Modifier.weight(1f)
            )
            // Weight
            InfoCard(
                title = "Berat",
                value = "${pokemon.weightInKg} kg",
                modifier = Modifier.weight(1f)
            )
            // Base Exp
            InfoCard(
                title = "Base EXP",
                value = "${pokemon.baseExperience ?: "N/A"}",
                modifier = Modifier.weight(1f)
            )
        }

        // Abilities Section
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = DarkCard)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Kemampuan",
                    style = MaterialTheme.typography.titleMedium,
                    color = PokemonYellow,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    pokemon.abilities.forEach { abilitySlot ->
                        Box(
                            modifier = Modifier
                                .background(
                                    color = if (abilitySlot.isHidden) Color(0x33FFD700) else Color(0x33CC0000),
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .padding(horizontal = 10.dp, vertical = 6.dp)
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = abilitySlot.ability.name.replace("-", " ")
                                        .replaceFirstChar { it.uppercase() },
                                    color = if (abilitySlot.isHidden) PokemonYellow else TextPrimary,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                                if (abilitySlot.isHidden) {
                                    Text(
                                        text = "(tersembunyi)",
                                        color = PokemonYellow.copy(alpha = 0.7f),
                                        fontSize = 9.sp
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        // Stats Section
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = DarkCard)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Base Stats",
                    style = MaterialTheme.typography.titleMedium,
                    color = PokemonYellow,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(12.dp))
                pokemon.stats.forEach { stat ->
                    StatBar(stat = stat, primaryColor = primaryTypeColor)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun TypeChip(typeName: String) {
    val typeColor = getPokemonTypeColor(typeName)
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(typeColor.copy(alpha = 0.8f))
            .padding(horizontal = 20.dp, vertical = 8.dp)
    ) {
        Text(
            text = typeName.replaceFirstChar { it.uppercase() },
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        )
    }
}

@Composable
fun InfoCard(
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = DarkCard)
    ) {
        Column(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = value,
                color = TextPrimary,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
            Text(
                text = title,
                color = TextSecondary,
                fontSize = 11.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun StatBar(
    stat: PokemonStat,
    primaryColor: Color
) {
    val statName = when (stat.stat.name) {
        "hp" -> "HP"
        "attack" -> "ATK"
        "defense" -> "DEF"
        "special-attack" -> "Sp.ATK"
        "special-defense" -> "Sp.DEF"
        "speed" -> "SPD"
        else -> stat.stat.name.replaceFirstChar { it.uppercase() }
    }

    val maxStat = 255f
    var animationPlayed by remember { mutableStateOf(false) }
    val progress by animateFloatAsState(
        targetValue = if (animationPlayed) stat.baseStat / maxStat else 0f,
        animationSpec = tween(durationMillis = 1000),
        label = "stat_animation"
    )

    LaunchedEffect(Unit) {
        animationPlayed = true
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = statName,
            color = TextSecondary,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.width(60.dp)
        )
        Text(
            text = stat.baseStat.toString().padStart(3, ' '),
            color = TextPrimary,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.width(36.dp)
        )
        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier
                .weight(1f)
                .height(8.dp)
                .clip(RoundedCornerShape(4.dp)),
            color = primaryColor,
            trackColor = Color(0xFF2D2D4E),
            strokeCap = StrokeCap.Round
        )
    }
}
