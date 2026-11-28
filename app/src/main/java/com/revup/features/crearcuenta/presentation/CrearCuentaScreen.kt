package com.revup.features.crearcuenta.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.TextObfuscationMode
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SecureTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.revup.core.ui.theme.RepairLinkAndroidTheme

@Composable
fun CrearCuentaScreen(
    modifier: Modifier = Modifier,
    onVolver: () -> Unit = {},
    onIniciarSesion: () -> Unit = {},
    viewModel: CrearCuentaViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val aceptarCondiciones by viewModel.aceptarCondiciones.collectAsStateWithLifecycle()

    LaunchedEffect(uiState) {
        if (uiState is CrearCuentaUiState.Registered) {
            onIniciarSesion()
        }
    }

    CrearCuentaContent(
        modifier = modifier,
        uiState = uiState,
        nombreState = viewModel.nombreTextFieldState,
        correoState = viewModel.correoTextFieldState,
        telefonoState = viewModel.telefonoTextFieldState,
        contrasenaState = viewModel.contrasenaTextFieldState,
        aceptarCondiciones = aceptarCondiciones,
        onAceptarCondiciones = { viewModel.onAceptarCondiciones(it) },
        onVolver = onVolver,
        onIniciarSesion = onIniciarSesion,
        onCrearCuenta = { viewModel.register() }
    )
}

@Composable
private fun CrearCuentaContent(
    modifier: Modifier = Modifier,
    uiState: CrearCuentaUiState = CrearCuentaUiState.Initial,
    nombreState: TextFieldState,
    correoState: TextFieldState,
    telefonoState: TextFieldState,
    contrasenaState: TextFieldState,
    aceptarCondiciones: Boolean,
    onAceptarCondiciones: (Boolean) -> Unit,
    onVolver: () -> Unit = {},
    onIniciarSesion: () -> Unit = {},
    onCrearCuenta: () -> Unit = {}
) {
    var mostrarContrasena by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        // Cabecera

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .background(Color.White, CircleShape)
                    .clickable { onVolver() },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "‹",
                    fontSize = 32.sp,
                    color = Color.Black
                )
            }

            Text(
                text = "Crear Cuenta",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Text(
            text = "Reserva citas en talleres cercanos y sigue tus reparaciones",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.size(8.dp))

        // Nombre

        Text(
            text = "Nombre y apellidos",
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium
        )

        TextField(
            state = nombreState,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Laura Gómez") },
            lineLimits = TextFieldLineLimits.SingleLine,
            shape = RoundedCornerShape(12.dp),
            colors = coloresCampo()
        )

        // Correo electrónico

        Text(
            text = "Correo electrónico",
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium
        )

        TextField(
            state = correoState,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("laura@correo.com") },
            leadingIcon = {
                Text(text = "✉", fontSize = 18.sp)
            },
            lineLimits = TextFieldLineLimits.SingleLine,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            shape = RoundedCornerShape(12.dp),
            colors = coloresCampo()
        )

        // Teléfono

        Text(
            text = "Teléfono",
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium
        )

        TextField(
            state = telefonoState,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("600 123 456") },
            leadingIcon = {
                Text(text = "☎", fontSize = 18.sp)
            },
            lineLimits = TextFieldLineLimits.SingleLine,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone
            ),
            shape = RoundedCornerShape(12.dp),
            colors = coloresCampo()
        )

        // Contraseña

        Text(
            text = "Contraseña",
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium
        )

        SecureTextField(
            state = contrasenaState,
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Mínimo 8 caracteres") },
            leadingIcon = {
                Text(text = "🔒", fontSize = 18.sp)
            },
            trailingIcon = {
                Text(
                    text = if (mostrarContrasena) "◉" else "◎",
                    fontSize = 20.sp,
                    modifier = Modifier.clickable {
                        mostrarContrasena = !mostrarContrasena
                    }
                )
            },
            textObfuscationMode = if (mostrarContrasena) {
                TextObfuscationMode.Visible
            } else {
                TextObfuscationMode.RevealLastTyped
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            shape = RoundedCornerShape(12.dp),
            colors = coloresCampo()
        )

        // Aceptar condiciones

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = aceptarCondiciones,
                onCheckedChange = { onAceptarCondiciones(it) }
            )

            Text(
                text = "Acepto las condiciones de uso y la política de privacidad",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // Botón crear cuenta

        Button(
            onClick = {
                onCrearCuenta()
            },
            enabled = uiState !is CrearCuentaUiState.Registering &&
                    aceptarCondiciones,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = if (uiState is CrearCuentaUiState.Registering) {
                    "Creando cuenta..."
                } else {
                    "Crear cuenta"
                },
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(vertical = 6.dp)
            )
        }

        if (uiState is CrearCuentaUiState.Error) {
            Text(
                text = uiState.message,
                color = MaterialTheme.colorScheme.error
            )
        }

        // Iniciar sesión

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onIniciarSesion() }
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "¿Ya tienes cuenta? ",
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Text(
                text = "Inicia sesión",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
private fun coloresCampo() = TextFieldDefaults.colors(
    focusedContainerColor = Color.White,
    unfocusedContainerColor = Color.White,
    focusedIndicatorColor = Color.Transparent,
    unfocusedIndicatorColor = Color.Transparent,
    focusedTextColor = Color.Black,
    unfocusedTextColor = Color.Black,
    focusedPlaceholderColor = Color.Gray,
    unfocusedPlaceholderColor = Color.Gray
)

// Preview

@Preview(
    showBackground = true,
    widthDp = 390,
    heightDp = 844
)
@Composable
fun CrearCuentaScreenPreview() {
    RepairLinkAndroidTheme {
        CrearCuentaContent(
            nombreState = TextFieldState(),
            correoState = TextFieldState(),
            telefonoState = TextFieldState(),
            contrasenaState = TextFieldState(),
            aceptarCondiciones = false,
            onAceptarCondiciones = {}
        )
    }
}
