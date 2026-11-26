package com.revup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revup.features.auth.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<MainUiState>(MainUiState.Cargando)
    val uiState: StateFlow<MainUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val token = repository.token.first()
            if (token == null) {
                _uiState.value = MainUiState.SinSesion
            } else {
                _uiState.value = MainUiState.ConSesion
            }
        }
    }
}

sealed interface MainUiState {
    object Cargando : MainUiState
    object SinSesion : MainUiState
    object ConSesion : MainUiState
}
