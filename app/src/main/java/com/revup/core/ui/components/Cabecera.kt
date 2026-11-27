package com.revup.core.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.revup.core.ui.theme.RepairLinkAndroidTheme

@Composable
fun Cabecera(
    titulo: String,
    subtitulo: String,
    onVolver: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BotonVolver(onVolver = onVolver)
        Column(modifier = Modifier.weight(1f)) {
            Text(text = titulo, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text(text = subtitulo, fontSize = 12.sp)
        }
    }
}

@Preview(showBackground = true, widthDp = 390)
@Composable
fun CabeceraPreview() {
    RepairLinkAndroidTheme {
        Cabecera(
            titulo = "Jueves 8 de octubre",
            subtitulo = "Talleres Hermanos Ruiz",
            onVolver = {}
        )
    }
}
