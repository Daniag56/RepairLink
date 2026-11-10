package com.revup.core.ui.components

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.revup.core.ui.theme.RepairLinkAndroidTheme

@Composable
fun BarraInferior(
    seleccionada: String,
    onTalleres: () -> Unit,
    onMisCoches: () -> Unit,
    onPerfil: () -> Unit,
    modifier: Modifier = Modifier
) {
    NavigationBar(modifier = modifier) {
        NavigationBarItem(
            selected = seleccionada == "Talleres",
            onClick = { onTalleres() },
            icon = { Text("🔧") },
            label = { Text("Talleres") }
        )

        NavigationBarItem(
            selected = seleccionada == "Mis coches",
            onClick = { onMisCoches() },
            icon = { Text("🚗") },
            label = { Text("Mis coches") }
        )

        NavigationBarItem(
            selected = seleccionada == "Perfil",
            onClick = { onPerfil() },
            icon = { Text("👤") },
            label = { Text("Perfil") }
        )
    }
}

@Preview(showBackground = true, widthDp = 390)
@Composable
fun BarraInferiorPreview() {
    RepairLinkAndroidTheme {
        BarraInferior(
            seleccionada = "Mis coches",
            onTalleres = {},
            onMisCoches = {},
            onPerfil = {}
        )
    }
}
