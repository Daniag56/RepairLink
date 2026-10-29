package com.revup.features.home.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun HomeScreen(
    onReparaciones: () -> Unit
) {
    Column {
        Text(text = "Menú principal")

        Button(onClick = { onReparaciones() }) {
            Text(text = "Mis reparaciones")
        }
    }
}
