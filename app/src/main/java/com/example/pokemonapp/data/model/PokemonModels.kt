package com.example.pokemonapp.data.model

import com.google.gson.annotations.SerializedName

data class PokemonListResponse(
    @SerializedName("count") val count: Int,
    @SerializedName("next") val next: String?,
    @SerializedName("previous") val previous: String?,
    @SerializedName("results") val results: List<PokemonResult>
)

data class PokemonResult(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
) {
    val id: Int
        get() {
            val parts = url.trimEnd('/').split("/")
            return parts.last().toIntOrNull() ?: 0
        }

    val imageUrl: String
        get() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
}

data class PokemonDetail(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("height") val height: Int,
    @SerializedName("weight") val weight: Int,
    @SerializedName("base_experience") val baseExperience: Int?,
    @SerializedName("types") val types: List<PokemonTypeSlot>,
    @SerializedName("stats") val stats: List<PokemonStat>,
    @SerializedName("abilities") val abilities: List<PokemonAbilitySlot>,
    @SerializedName("sprites") val sprites: PokemonSprites
) {
    val imageUrl: String
        get() = sprites.other?.officialArtwork?.frontDefault
            ?: sprites.frontDefault
            ?: "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"

    val heightInMeters: Double get() = height / 10.0
    val weightInKg: Double get() = weight / 10.0
}

data class PokemonTypeSlot(
    @SerializedName("slot") val slot: Int,
    @SerializedName("type") val type: PokemonType
)

data class PokemonType(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)

data class PokemonStat(
    @SerializedName("base_stat") val baseStat: Int,
    @SerializedName("effort") val effort: Int,
    @SerializedName("stat") val stat: StatInfo
)

data class StatInfo(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)

data class PokemonAbilitySlot(
    @SerializedName("ability") val ability: AbilityInfo,
    @SerializedName("is_hidden") val isHidden: Boolean,
    @SerializedName("slot") val slot: Int
)

data class AbilityInfo(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)

data class PokemonSprites(
    @SerializedName("front_default") val frontDefault: String?,
    @SerializedName("other") val other: OtherSprites?
)

data class OtherSprites(
    @SerializedName("official-artwork") val officialArtwork: OfficialArtwork?
)

data class OfficialArtwork(
    @SerializedName("front_default") val frontDefault: String?
)
