package com.example.pokemonapp.data.repository

import com.example.pokemonapp.data.model.PokemonDetail
import com.example.pokemonapp.data.model.PokemonListResponse
import com.example.pokemonapp.data.remote.RetrofitInstance

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val message: String) : Result<Nothing>()
    object Loading : Result<Nothing>()
}

class PokemonRepository {

    private val apiService = RetrofitInstance.pokemonApiService

    suspend fun getPokemonList(limit: Int = 20, offset: Int = 0): Result<PokemonListResponse> {
        return try {
            val response = apiService.getPokemonList(limit, offset)
            Result.Success(response)
        } catch (e: Exception) {
            Result.Error(e.message ?: "Unknown error occurred")
        }
    }

    suspend fun getPokemonDetail(nameOrId: String): Result<PokemonDetail> {
        return try {
            val response = apiService.getPokemonDetail(nameOrId)
            Result.Success(response)
        } catch (e: Exception) {
            Result.Error(e.message ?: "Unknown error occurred")
        }
    }
}
