package com.revup.features.elegirdia.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ElegirDiaViewModel @Inject constructor() : ViewModel() {

    val semanas = listOf(
        listOf(
            DiaUi(0, "Vacío"),
            DiaUi(0, "Vacío"),
            DiaUi(0, "Vacío"),
            DiaUi(1, "No disponible"),
            DiaUi(2, "No disponible"),
            DiaUi(3, "No disponible"),
            DiaUi(4, "No disponible")
        ),
        listOf(
            DiaUi(5, "No disponible"),
            DiaUi(6, "Hoy"),
            DiaUi(7, "Completo"),
            DiaUi(8, "Seleccionado"),
            DiaUi(9, "Pocas horas"),
            DiaUi(10, "Libre"),
            DiaUi(11, "No disponible")
        ),
        listOf(
            DiaUi(12, "Libre"),
            DiaUi(13, "Completo"),
            DiaUi(14, "Completo"),
            DiaUi(15, "Pocas horas"),
            DiaUi(16, "Libre"),
            DiaUi(17, "Libre"),
            DiaUi(18, "No disponible")
        ),
        listOf(
            DiaUi(19, "Libre"),
            DiaUi(20, "Completo"),
            DiaUi(21, "Pocas horas"),
            DiaUi(22, "Libre"),
            DiaUi(23, "Libre"),
            DiaUi(24, "Libre"),
            DiaUi(25, "No disponible")
        ),
        listOf(
            DiaUi(26, "Libre"),
            DiaUi(27, "Completo"),
            DiaUi(28, "Pocas horas"),
            DiaUi(29, "Libre"),
            DiaUi(30, "Libre"),
            DiaUi(31, "Libre"),
            DiaUi(0, "Vacío")
        )
    )
}
