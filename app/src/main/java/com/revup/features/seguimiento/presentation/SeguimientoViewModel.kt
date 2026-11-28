package com.revup.features.seguimiento.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SeguimientoViewModel @Inject constructor() : ViewModel() {

    val coche = "Renault Clio 3"

    val detalle = "4821KM - TALLER Hermano Ruiz"

    val estado = "En reparacion"
}
