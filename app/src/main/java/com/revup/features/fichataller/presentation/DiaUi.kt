package com.revup.features.fichataller.presentation

// Un día del calendario.
// estado puede ser: "Vacío", "No disponible", "Libre", "Hoy", "Pocas horas", "Completo" o "Seleccionado"
data class DiaUi(
    val numero: Int,
    val estado: String
)
