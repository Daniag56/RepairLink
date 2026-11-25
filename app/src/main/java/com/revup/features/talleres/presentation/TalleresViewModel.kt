package com.revup.features.talleres.presentation


import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


@HiltViewModel
class TalleresViewModel @Inject constructor() : ViewModel() {

    //Variable que guarda el estado de la pantalla (para usar el StateFlow)
    private val _uiState = MutableStateFlow<TalleresUiState>(value = TalleresUiState.Initial)

    // Variable pública para que la pantalla observe el estado
    val uiState = _uiState.asStateFlow()
}

    sealed class TalleresUiState {
        object Initial: TalleresUiState()
        object Loading : TalleresUiState()
        object Empty : TalleresUiState()

        data class Error(val message: String): TalleresUiState()
    }