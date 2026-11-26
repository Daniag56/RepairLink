package com.revup.features.perfil.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class PerfilViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow<PerfilUiState>(
        PerfilUiState.Initial
    )
    val uiState = _uiState.asStateFlow()

    private val perfilDeEjemplo = PerfilUiState.Success(
        nombre = "Laura Gómez",
        correo = "laura.gomez@email.com",
        iniciales = "LG",
        notificaciones = 2
    )

    init {
        cargarPerfil()
    }

    fun cargarPerfil() {
        viewModelScope.launch {
            _uiState.value = PerfilUiState.Loading

            delay(1500)

            _uiState.value = perfilDeEjemplo
        }
    }
}

sealed class PerfilUiState {

    object Initial : PerfilUiState()

    object Loading : PerfilUiState()

    object Empty : PerfilUiState()

    data class Success(
        val nombre: String,
        val correo: String,
        val iniciales: String,
        val notificaciones: Int
    ) : PerfilUiState()

    data class Error(
        val message: String
    ) : PerfilUiState()
}