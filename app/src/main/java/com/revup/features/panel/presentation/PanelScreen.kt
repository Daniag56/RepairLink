package com.revup.features.panel.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@Composable
fun PanelScreen(
    onAceptarCoche: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: PanelViewModel = hiltViewModel()
) {
    Column(
        modifier = modifier.padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Talleres Hermanos Ruiz", fontSize = 24.sp, fontWeight = FontWeight.Bold)

        Card(modifier = Modifier.fillMaxWidth()) {
            Text(text = "Citas de hoy: 3", modifier = Modifier.padding(16.dp))
        }

        Button(onClick = { onAceptarCoche() }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Aceptar coche")
        }
    }
}