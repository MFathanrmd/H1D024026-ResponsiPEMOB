package com.example.pokemonapp.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemonapp.data.model.PokemonDetail
import com.example.pokemonapp.data.repository.PokemonRepository
import com.example.pokemonapp.data.repository.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class DetailUiState(
    val pokemon: PokemonDetail? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

class DetailViewModel : ViewModel() {

    private val repository = PokemonRepository()

    private val _uiState = MutableStateFlow(DetailUiState())
    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()

    fun loadPokemonDetail(nameOrId: String) {
        viewModelScope.launch {
            _uiState.value = DetailUiState(isLoading = true)
            when (val result = repository.getPokemonDetail(nameOrId)) {
                is Result.Success -> {
                    _uiState.value = DetailUiState(pokemon = result.data)
                }
                is Result.Error -> {
                    _uiState.value = DetailUiState(error = result.message)
                }
                is Result.Loading -> {}
            }
        }
    }
}
