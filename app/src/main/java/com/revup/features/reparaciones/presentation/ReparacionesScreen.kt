package com.revup.features.reparaciones.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ReparacionesScreen(
    onHome: () -> Unit
)
 {
    Column {
        Text(text = "Reparaciones de coches")

        Button(onClick = { onHome() }) {
            Text(text = "Volver a Home")
        }
    }
}
