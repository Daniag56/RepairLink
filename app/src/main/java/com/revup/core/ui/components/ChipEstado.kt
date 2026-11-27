package com.revup.core.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.revup.core.ui.theme.RepairLinkAndroidTheme

@Composable
fun ChipEstado(
    texto: String,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        Text(
            text = texto,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ChipEstadoPreview() {
    RepairLinkAndroidTheme {
        ChipEstado(texto = "En reparación")
    }
}
