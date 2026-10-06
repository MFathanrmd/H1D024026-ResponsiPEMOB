package com.example.pokemonapp.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapp.data.model.PokemonResult
import com.example.pokemonapp.data.repository.PokemonRepository
import com.example.pokemonapp.data.repository.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class HomeUiState(
    val pokemonList: List<PokemonResult> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val searchQuery: String = "",
    val isLoadingMore: Boolean = false,
    val canLoadMore: Boolean = true
)

class HomeViewModel : ViewModel() {

    private val repository = PokemonRepository()

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private var currentOffset = 0
    private val pageSize = 20
    private var allPokemonList = mutableListOf<PokemonResult>()

    init {
        loadPokemon()
    }

    fun loadPokemon() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            when (val result = repository.getPokemonList(limit = pageSize, offset = 0)) {
                is Result.Success -> {
                    allPokemonList.clear()
                    allPokemonList.addAll(result.data.results)
                    currentOffset = pageSize
                    _uiState.value = _uiState.value.copy(
                        pokemonList = allPokemonList.toList(),
                        isLoading = false,
                        canLoadMore = result.data.next != null
                    )
                }
                is Result.Error -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = result.message
                    )
                }
                is Result.Loading -> {}
            }
        }
    }

    fun loadMorePokemon() {
        if (_uiState.value.isLoadingMore || !_uiState.value.canLoadMore) return
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoadingMore = true)
            when (val result = repository.getPokemonList(limit = pageSize, offset = currentOffset)) {
                is Result.Success -> {
                    allPokemonList.addAll(result.data.results)
                    currentOffset += pageSize
                    _uiState.value = _uiState.value.copy(
                        pokemonList = allPokemonList.toList(),
                        isLoadingMore = false,
                        canLoadMore = result.data.next != null
                    )
                }
                is Result.Error -> {
                    _uiState.value = _uiState.value.copy(
                        isLoadingMore = false,
                        error = result.message
                    )
                }
                is Result.Loading -> {}
            }
        }
    }

    fun onSearchQueryChange(query: String) {
        _uiState.value = _uiState.value.copy(searchQuery = query)
        filterPokemon(query)
    }

    private fun filterPokemon(query: String) {
        val filtered = if (query.isEmpty()) {
            allPokemonList.toList()
        } else {
            allPokemonList.filter { pokemon ->
                pokemon.name.contains(query.trim().lowercase()) ||
                        pokemon.id.toString().contains(query.trim())
            }
        }
        _uiState.value = _uiState.value.copy(pokemonList = filtered)
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }
}
