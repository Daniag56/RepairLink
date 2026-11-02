package com.revup.features.miscoches.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MisCochesViewModel @Inject constructor() : ViewModel() {


    val coches = listOf(
        CocheUi(
            id = 1,
            nombre = "Renault Clio 3",
            matricula = "4821 KLM",
            taller = "Talleres Hermanos Ruiz",
            estado = "En reparación",
            paso = "Cambio de pastillas y discos",
            pasoActual = 5,
            pasosTotales = 6
        ),
        CocheUi(
            id = 2,
            nombre = "SEAT León",
            matricula = "7730 HPT",


            taller = "AutoService Costa",
            estado = "En revisión",
            paso = "Diagnóstico en curso",
            pasoActual = 2,
            pasosTotales = 6
        ),
        CocheUi(
            id = 3,
            nombre = "Peugeot 208",
            matricula = "1596 MXC",
            taller = "Neumáticos y Frenos Delta",
            estado = "Listo para recoger",
            paso = "Ya puedes pasar a recogerlo",
            pasoActual = 6,
            pasosTotales = 6
        )
    )
}