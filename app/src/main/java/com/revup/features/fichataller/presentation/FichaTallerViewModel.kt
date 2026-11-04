package com.revup.features.fichataller.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FichaTallerViewModel @Inject constructor() : ViewModel() {

    // Octubre de 2026 empieza en jueves: los tres primeros huecos (L, M, X) van vacíos.
    // Son 35 casillas = 5 semanas de 7 días.
    val dias = listOf(
        // Semana 1
        DiaUi(0, "Vacío"),
        DiaUi(0, "Vacío"),
        DiaUi(0, "Vacío"),
        DiaUi(1, "No disponible"),
        DiaUi(2, "No disponible"),
        DiaUi(3, "No disponible"),
        DiaUi(4, "No disponible"),
        // Semana 2
        DiaUi(5, "No disponible"),
        DiaUi(6, "Hoy"),
        DiaUi(7, "Completo"),
        DiaUi(8, "Seleccionado"),
        DiaUi(9, "Pocas horas"),
        DiaUi(10, "Libre"),
        DiaUi(11, "No disponible"),
        // Semana 3
        DiaUi(12, "Libre"),
        DiaUi(13, "Completo"),
        DiaUi(14, "Completo"),
        DiaUi(15, "Pocas horas"),
        DiaUi(16, "Libre"),
        DiaUi(17, "Libre"),
        DiaUi(18, "No disponible"),
        // Semana 4
        DiaUi(19, "Libre"),
        DiaUi(20, "Completo"),
        DiaUi(21, "Pocas horas"),
        DiaUi(22, "Libre"),
        DiaUi(23, "Libre"),
        DiaUi(24, "Libre"),
        DiaUi(25, "No disponible"),
        // Semana 5
        DiaUi(26, "Libre"),
        DiaUi(27, "Completo"),
        DiaUi(28, "Pocas horas"),
        DiaUi(29, "Libre"),
        DiaUi(30, "Libre"),
        DiaUi(31, "Libre"),
        DiaUi(0, "Vacío")
    )
}
