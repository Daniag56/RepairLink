package com.revup.core.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.revup.core.ui.theme.RepairLinkAndroidTheme

@Composable
fun BotonSecundario(
    texto: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    OutlinedButton(
        onClick = { onClick() },
        modifier = modifier.fillMaxWidth(),
        enabled = enabled
    ) {
        Text(text = texto, fontSize = 16.sp)
    }
}

@Preview(showBackground = true, widthDp = 390)
@Composable
fun BotonSecundarioPreview() {
    RepairLinkAndroidTheme {
        BotonSecundario(texto = "Ver mis citas", onClick = {})
    }
}
