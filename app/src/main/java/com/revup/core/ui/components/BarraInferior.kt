package com.revup.core.ui.components

import androidx.compose.material3.Icon
import androidx.compose.ui.res.painterResource
import com.revup.R
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
            icon = { Icon(painter = painterResource(R.drawable.ic_pin), contentDescription = null) },
            label = { Text("Talleres") }
        )

        NavigationBarItem(
            selected = seleccionada == "Mis coches",
            onClick = { onMisCoches() },
            icon = { Icon(painter = painterResource(R.drawable.ic_car_tab), contentDescription = null) },
            label = { Text("Mis coches") }
        )

        NavigationBarItem(
            selected = seleccionada == "Perfil",
            onClick = { onPerfil() },
            icon = { Icon(painter = painterResource(R.drawable.ic_user), contentDescription = null) },
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
