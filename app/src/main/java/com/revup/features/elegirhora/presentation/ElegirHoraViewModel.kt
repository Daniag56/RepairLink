package com.revup.features.elegirhora.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ElegirHoraViewModel @Inject constructor() : ViewModel() {

    val filasHoras = listOf(
        listOf(
            HoraUi("09:00", true),
            HoraUi("09:30", false),
            HoraUi("10:00", true)
        ),
        listOf(
            HoraUi("10:30", true),
            HoraUi("11:00", false),
            HoraUi("11:30", true)
        ),
        listOf(
            HoraUi("12:00", false),
            HoraUi("16:00", false),
            HoraUi("16:30", true)
        ),
        listOf(
            HoraUi("17:00", false),
            HoraUi("17:30", true),
            HoraUi("18:00", false)
        )
    )

    val filasMotivos = listOf(
        listOf("Revisión", "Frenos", "Aceite"),
        listOf("Neumáticos", "Otro")
    )

    val horaElegida = "10:30"

    val motivoElegido = "Revisión"
}
