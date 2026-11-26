package com.revup.features.talleres.presentation


import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay


@HiltViewModel
class TalleresViewModel @Inject constructor() : ViewModel() {

    //Variable que guarda el estado de la pantalla (para usar el StateFlow)
    private val _uiState = MutableStateFlow<TalleresUiState>(value = TalleresUiState.Initial)

    // Variable pública para que la pantalla observe el estado
    val uiState = _uiState.asStateFlow()


    val talleresDeEjemplo = listOf(
        TallerUi(
            nombre = "Talleres Hermanos Ruiz",
            destacado = true,
            valoracion = 4.9,
            numeroResenas = 312,
            distancia = "0,8 km",
            direccion = "C/ Ejemplo 12",
            servicios = listOf("Mecánica", "Frenos", "ITV", "Aceite"),
            disponibilidad = "Hoy 16:30"
        ),
        TallerUi(
            nombre = "AutoService Costa",
            destacado = false,
            valoracion = 4.8,
            numeroResenas = 204,
            distancia = "1,2 km",
            direccion = "",
            servicios = emptyList(),
            disponibilidad = "Mañana · 09:00"
        )
    )
    fun cargarTalleres() {
        viewModelScope.launch {
            _uiState.value = TalleresUiState.Loading
            //Esta linea la añado para simular que carga pero debe quitarse cuando conectemos con la API
            delay(1500)
            _uiState.value = TalleresUiState.Success(
                talleres = talleresDeEjemplo
            )
        }
    }

    init {
        cargarTalleres()
    }
}
    sealed class TalleresUiState {
        object Initial: TalleresUiState()
        object Loading : TalleresUiState()
        object Empty : TalleresUiState()

        data class Error(val message: String): TalleresUiState()
        data class Success(val talleres: List<TallerUi>): TalleresUiState()
    }