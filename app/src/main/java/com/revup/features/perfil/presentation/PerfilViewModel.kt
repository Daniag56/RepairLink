package com.revup.features.perfil.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PerfilViewModel @Inject constructor() : ViewModel() {

    val nombre = "Laura Gómez"
    val correo = "laura.gomez@email.com"
    val iniciales = "LG"
    val notificaciones = 2
}


