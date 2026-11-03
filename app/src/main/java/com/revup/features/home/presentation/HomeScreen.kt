
package com.revup.features.home.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen(
    onTalleres: () -> Unit,
    onMisCoches: () -> Unit,
    onPerfil: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        Column {
            Text(text = "Menú principal")

            Button(onClick = { onMisCoches() }) {
                Text(text = "Mis reparaciones")
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        NavigationBar {

            NavigationBarItem(
                selected = false,
                onClick = { onTalleres() },
                icon = { Text("🔧") },
                label = { Text("Talleres") }
            )

            NavigationBarItem(
                selected = false,
                onClick = { onMisCoches() },
                icon = { Text("🚗") },
                label = { Text("Mis coches") }
            )

            NavigationBarItem(
                selected = false,
                onClick = { onPerfil() },
                icon = { Text("👤") },
                label = { Text("Perfil") }
            )
        }
    }
}
