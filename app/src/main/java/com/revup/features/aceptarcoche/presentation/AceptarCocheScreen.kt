package com.revup.features.aceptarcoche.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@Composable
fun AceptarCocheScreen(
    onVolver: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AceptarCocheViewModel = hiltViewModel()
) {
    Column(modifier = modifier) {
        Text(text = "Aceptar coche")

        Button(onClick = { onVolver() }) {
            Text(text = "Volver")
        }
    }
}
