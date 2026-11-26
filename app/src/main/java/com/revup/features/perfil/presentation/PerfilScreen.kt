package com.revup.features.perfil.presentation

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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.revup.R
import com.revup.core.ui.components.BarraInferior
import com.revup.core.ui.theme.RepairLinkAndroidTheme
import com.revup.core.ui.theme.RepairLinkColors

@Composable
fun PerfilScreen(
    onTalleres: () -> Unit,
    onMisCoches: () -> Unit,
    onPerfil: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: PerfilViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    PerfilContent(
        uiState = uiState,
        onTalleres = onTalleres,
        onMisCoches = onMisCoches,
        onPerfil = onPerfil,
        onReintentar = viewModel::cargarPerfil,
        modifier = modifier
    )
}

@Composable
fun PerfilContent(
    uiState: PerfilUiState,
    onTalleres: () -> Unit,
    onMisCoches: () -> Unit,
    onPerfil: () -> Unit,
    onReintentar: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            BarraInferior(
                seleccionada = "Perfil",
                onTalleres = onTalleres,
                onMisCoches = onMisCoches,
                onPerfil = onPerfil
            )
        }
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(20.dp),
            contentAlignment = Alignment.Center
        ) {
            when (uiState) {

                PerfilUiState.Initial -> {
                    Text(
                        text = "Preparando perfil...",
                        fontSize = 16.sp
                    )
                }

                PerfilUiState.Loading -> {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        CircularProgressIndicator()

                        Text(
                            text = "Cargando perfil...",
                            fontSize = 16.sp
                        )
                    }
                }

                PerfilUiState.Empty -> {
                    Text(
                        text = "No hay datos de perfil",
                        fontSize = 16.sp
                    )
                }

                is PerfilUiState.Success -> {
                    PerfilDatosContent(
                        nombre = uiState.nombre,
                        correo = uiState.correo,
                        iniciales = uiState.iniciales,
                        notificaciones = uiState.notificaciones
                    )
                }

                is PerfilUiState.Error -> {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(
                            text = uiState.message,
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.error
                        )

                        Button(
                            onClick = onReintentar
                        ) {
                            Text("Reintentar")
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun PerfilDatosContent(
    nombre: String,
    correo: String,
    iniciales: String,
    notificaciones: Int
) {
    var avisosActivos by remember {
        mutableStateOf(true)
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Card(
                modifier = Modifier.size(64.dp),
                shape = CircleShape
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = iniciales,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = nombre,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = correo,
                    fontSize = 13.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                FilaPerfil("◷", "Mis citas")

                Divider()

                FilaPerfil(
                    titulo = "Mis coches",
                    iconoResource = R.drawable.ic_car_tab
                )

                Divider()

                FilaPerfil(
                    "●",
                    "Notificaciones",
                    notificaciones
                )

                Divider()

                FilaPerfil("◇", "Privacidad y seguridad")

                Divider()

                FilaPerfil("?", "Ayuda")
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Avisos en el móvil",
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.weight(1f)
                )

                Switch(
                    checked = avisosActivos,
                    onCheckedChange = {
                        avisosActivos = it
                    },
                    colors = SwitchDefaults.colors(
                        checkedTrackColor = RepairLinkColors.Success,
                        checkedThumbColor = RepairLinkColors.White
                    )
                )
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "↪",
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.error
                )

                Text(
                    text = "Cerrar sesión",
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }

        Spacer(
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun FilaPerfil(
    icono: String = "",
    titulo: String,
    notificaciones: Int = 0,
    iconoResource: Int = 0
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        if (iconoResource != 0) {
            Icon(
                painter = painterResource(iconoResource),
                contentDescription = null,
                modifier = Modifier.size(20.dp),
                tint = MaterialTheme.colorScheme.primary
            )
        } else {
            Text(
                text = icono,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.primary
            )
        }

        Text(
            text = titulo,
            modifier = Modifier.weight(1f),
            fontSize = 15.sp
        )

        if (notificaciones > 0) {
            Card(
                modifier = Modifier.size(24.dp),
                shape = CircleShape,
                colors = CardDefaults.cardColors(
                    containerColor = RepairLinkColors.OrangePrimary
                )
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = notificaciones.toString(),
                        color = RepairLinkColors.Black,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Text(
            text = "›",
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

// PREVIEWS

@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
fun PerfilInitialPreview() {
    RepairLinkAndroidTheme {
        PerfilContent(
            uiState = PerfilUiState.Initial,
            onTalleres = {},
            onMisCoches = {},
            onPerfil = {},
            onReintentar = {}
        )
    }
}

@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
fun PerfilLoadingPreview() {
    RepairLinkAndroidTheme {
        PerfilContent(
            uiState = PerfilUiState.Loading,
            onTalleres = {},
            onMisCoches = {},
            onPerfil = {},
            onReintentar = {}
        )
    }
}

@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
fun PerfilEmptyPreview() {
    RepairLinkAndroidTheme {
        PerfilContent(
            uiState = PerfilUiState.Empty,
            onTalleres = {},
            onMisCoches = {},
            onPerfil = {},
            onReintentar = {}
        )
    }
}

@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
fun PerfilSuccessPreview() {
    RepairLinkAndroidTheme {
        PerfilContent(
            uiState = PerfilUiState.Success(
                nombre = "Laura Gómez",
                correo = "laura.gomez@email.com",
                iniciales = "LG",
                notificaciones = 3
            ),
            onTalleres = {},
            onMisCoches = {},
            onPerfil = {},
            onReintentar = {}
        )
    }
}

@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
fun PerfilErrorPreview() {
    RepairLinkAndroidTheme {
        PerfilContent(
            uiState = PerfilUiState.Error(
                message = "No se ha podido cargar el perfil"
            ),
            onTalleres = {},
            onMisCoches = {},
            onPerfil = {},
            onReintentar = {}
        )
    }
}
