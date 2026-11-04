package com.revup.features.miscoches.presentation

data class CocheUi(
    val id: Int,
    val nombre: String,
    val matricula: String,
    val taller: String,
    val estado: String,
    val paso: String,
    val pasoActual: Int,
    val pasosTotales: Int
)
