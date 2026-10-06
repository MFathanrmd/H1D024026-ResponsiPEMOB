package com.example.pokemonapp.ui.theme

import androidx.compose.ui.graphics.Color

// Primary Palette - Pokémon Red/Blue inspired
val PokemonRed = Color(0xFFCC0000)
val PokemonRedLight = Color(0xFFFF3333)
val PokemonRedDark = Color(0xFF990000)

val PokemonBlue = Color(0xFF1E3A8A)
val PokemonBlueLight = Color(0xFF3B82F6)
val PokemonBlueDark = Color(0xFF1E2D7D)

val PokemonYellow = Color(0xFFFFCB05)
val PokemonYellowLight = Color(0xFFFFE066)

val PokemonGold = Color(0xFFB8860B)

// Background colors
val DarkBackground = Color(0xFF0F0F1A)
val DarkSurface = Color(0xFF1A1A2E)
val DarkCard = Color(0xFF16213E)
val DarkCardVariant = Color(0xFF0F3460)

// Text colors
val TextPrimary = Color(0xFFFFFFFF)
val TextSecondary = Color(0xFFB0B8C8)
val TextTertiary = Color(0xFF6B7280)

// Pokemon Type Colors
val TypeFire = Color(0xFFFF6B35)
val TypeWater = Color(0xFF4A90D9)
val TypeGrass = Color(0xFF4CAF50)
val TypeElectric = Color(0xFFFFD700)
val TypePsychic = Color(0xFFE91E8C)
val TypeIce = Color(0xFF74D0F1)
val TypeDragon = Color(0xFF7038F8)
val TypeDark = Color(0xFF403C3C)
val TypeFighting = Color(0xFFC22E28)
val TypePoison = Color(0xFFA33EA1)
val TypeGround = Color(0xFFE2BF65)
val TypeFlying = Color(0xFFA98FF3)
val TypeBug = Color(0xFFA6B91A)
val TypeRock = Color(0xFFB6A136)
val TypeGhost = Color(0xFF735797)
val TypeSteel = Color(0xFFB7B7CE)
val TypeNormal = Color(0xFFA8A878)
val TypeFairy = Color(0xFFD685AD)

fun getPokemonTypeColor(type: String): Color {
    return when (type.lowercase()) {
        "fire" -> TypeFire
        "water" -> TypeWater
        "grass" -> TypeGrass
        "electric" -> TypeElectric
        "psychic" -> TypePsychic
        "ice" -> TypeIce
        "dragon" -> TypeDragon
        "dark" -> TypeDark
        "fighting" -> TypeFighting
        "poison" -> TypePoison
        "ground" -> TypeGround
        "flying" -> TypeFlying
        "bug" -> TypeBug
        "rock" -> TypeRock
        "ghost" -> TypeGhost
        "steel" -> TypeSteel
        "fairy" -> TypeFairy
        else -> TypeNormal
    }
}
