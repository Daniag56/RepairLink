package com.revup.features.panel.presentation


import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@Composable
fun PanelScreen(
    onAceptarCoche: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: PanelViewModel = hiltViewModel()
) {
    Column(modifier = modifier) {
        Text(text = "Talleres Hermanos Ruiz")

        Button(onClick = { onAceptarCoche() }) {
            Text(text = "Aceptar coche")
        }
    }
}
