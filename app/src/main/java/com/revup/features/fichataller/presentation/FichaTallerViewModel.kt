package com.revup.features.fichataller.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FichaTallerViewModel @Inject constructor() : ViewModel() {

    val nombre = "Talleres Hermanos Ruiz"
}
