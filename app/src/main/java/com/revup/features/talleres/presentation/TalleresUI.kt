package com.revup.features.talleres.presentation

data class TallerUi(
    val nombre: String,
    val destacado: Boolean,
    val valoracion: Double,
    val numeroResenas: Int,
    val distancia: String,
    val direccion: String,
    val servicios: List<String>,
    val disponibilidad: String
)
