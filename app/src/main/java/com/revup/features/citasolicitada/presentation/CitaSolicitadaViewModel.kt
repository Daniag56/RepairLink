package com.revup.features.citasolicitada.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CitaSolicitadaViewModel @Inject constructor() : ViewModel() {

    val taller = "Talleres Hermanos Ruiz"
    val dia = "Jueves 8 de octubre"
    val hora = "10:30"
    val vehiculo = "Renault Clio 3"
    val motivo = "Revisión"
}
