package com.revup.core.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedSecureTextField
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.revup.core.ui.theme.RepairLinkAndroidTheme

@Composable
fun CampoTexto(
    etiqueta: String,
    state: TextFieldState,
    modifier: Modifier = Modifier,
    error: String? = null,
    enabled: Boolean = true
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        OutlinedTextField(
            state = state,
            label = { Text(etiqueta) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            isError = error != null,
            lineLimits = TextFieldLineLimits.SingleLine
        )
        if (error != null) {
            Text(text = error, fontSize = 12.sp, color = MaterialTheme.colorScheme.error)
        }
    }
}

@Composable
fun CampoContrasena(
    etiqueta: String,
    state: TextFieldState,
    modifier: Modifier = Modifier,
    error: String? = null,
    enabled: Boolean = true
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        OutlinedSecureTextField(
            state = state,
            label = { Text(etiqueta) },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            isError = error != null
        )
        if (error != null) {
            Text(text = error, fontSize = 12.sp, color = MaterialTheme.colorScheme.error)
        }
    }
}

@Preview(showBackground = true, widthDp = 390)
@Composable
fun CampoTextoPreview() {
    RepairLinkAndroidTheme {
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            CampoTexto(etiqueta = "Correo electrónico", state = TextFieldState("laura@correo.com"))
            CampoContrasena(
                etiqueta = "Contraseña",
                state = TextFieldState("1234"),
                error = "Mínimo 8 caracteres"
            )
        }
    }
}
