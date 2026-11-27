package com.revup.core.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.revup.core.ui.theme.RepairLinkAndroidTheme

@Composable
fun TituloPantalla(
    titulo: String,
    subtitulo: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(text = titulo, fontSize = 26.sp, fontWeight = FontWeight.Bold)
        Text(text = subtitulo, fontSize = 13.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun TituloPantallaPreview() {
    RepairLinkAndroidTheme {
        TituloPantalla(titulo = "Mis coches", subtitulo = "3 vehículos · 2 en el taller")
    }
}
