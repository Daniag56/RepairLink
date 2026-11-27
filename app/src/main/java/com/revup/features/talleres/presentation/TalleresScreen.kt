package com.revup.features.talleres.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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

@Composable
fun TalleresScreen(
    onVerTaller: () -> Unit,
    onTalleres: () -> Unit,
    onMisCoches: () -> Unit,
    onPerfil: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: TalleresViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    TalleresContent(
        uiState = uiState,
        onVerTaller = onVerTaller,
        onTalleres = onTalleres,
        onMisCoches = onMisCoches,
        onPerfil = onPerfil,
        onReintentar = { viewModel.cargarTalleres() },
        modifier = modifier
    )
}

@Composable
fun TalleresContent(
    uiState: TalleresUiState,
    onVerTaller: () -> Unit,
    onTalleres: () -> Unit,
    onMisCoches: () -> Unit,
    onPerfil: () -> Unit,
    onReintentar: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Text(
                text = "Talleres cerca de ti",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            )

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "🔍 Buscar taller o servicio",
                    modifier = Modifier.padding(16.dp),
                    fontSize = 14.sp
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(onClick = {}) {
                    Text("Todos")
                }

                Button(onClick = {}) {
                    Text("Mecánica")
                }

                Button(onClick = {}) {
                    Text("Neumáticos")
                }
            }

            // Aquí decidimos qué mostrar según el estado
            when (uiState) {

                is TalleresUiState.Initial -> {
                    CircularProgressIndicator()
                }

                is TalleresUiState.Loading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is TalleresUiState.Success -> {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        uiState.talleres.forEach { taller ->
                            TallerCard(
                                taller = taller,
                                onVerTaller = onVerTaller
                            )
                        }
                    }
                }

                is TalleresUiState.Empty -> {
                    Text(
                        text = "No hay talleres disponibles",
                        fontSize = 16.sp
                    )
                }

                is TalleresUiState.Error -> {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = uiState.message,
                            fontSize = 16.sp
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

        BarraInferior(
            seleccionada = "Talleres",
            onTalleres = onTalleres,
            onMisCoches = onMisCoches,
            onPerfil = onPerfil
        )
    }
}

@Composable
fun TallerCard(
    taller: TallerUi,
    onVerTaller: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (taller.destacado) {
                    Text(
                        text = "⭐ Destacado",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Icon(
                    painter = painterResource(R.drawable.ic_wrench),
                    contentDescription = null,
                    modifier = Modifier.size(26.dp)
                )
            }

            Text(
                text = taller.nombre,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            val valoracionTexto =
                taller.valoracion.toString().replace(".", ",")

            val direccionTexto =
                if (taller.direccion.isNotBlank()) {
                    " · ${taller.direccion}"
                } else {
                    ""
                }

            Text(
                text = "⭐ $valoracionTexto (${taller.numeroResenas}) · ${taller.distancia}$direccionTexto",
                fontSize = 12.sp
            )

            if (taller.servicios.isNotEmpty()) {
                Text(
                    text = taller.servicios.joinToString("   "),
                    fontSize = 12.sp
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "🕒 ${taller.disponibilidad}",
                    fontSize = 12.sp
                )

                Button(
                    onClick = onVerTaller
                ) {
                    Text(
                        text = "Ver calendario",
                        fontSize = 11.sp
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TalleresScreenPreview() {
    RepairLinkAndroidTheme {
        TalleresContent(
            uiState = TalleresUiState.Success(
                talleres = listOf(
                    TallerUi(
                        nombre = "Talleres Hermanos Ruiz",
                        destacado = true,
                        valoracion = 4.9,
                        numeroResenas = 312,
                        distancia = "0,8 km",
                        direccion = "C/ Ejemplo 12",
                        servicios = listOf(
                            "Mecánica",
                            "Frenos",
                            "ITV",
                            "Aceite"
                        ),
                        disponibilidad = "Hoy 16:30"
                    ),
                    TallerUi(
                        nombre = "AutoService Costa",
                        destacado = false,
                        valoracion = 4.8,
                        numeroResenas = 204,
                        distancia = "1,2 km",
                        direccion = "",
                        servicios = emptyList(),
                        disponibilidad = "Mañana · 09:00"
                    )
                )
            ),
            onVerTaller = {},
            onTalleres = {},
            onMisCoches = {},
            onPerfil = {},
            onReintentar = {}
        )
    }
}

@Preview(showBackground = true, name = "Talleres - Initial")
@Composable
fun TalleresInitialPreview() {
    RepairLinkAndroidTheme {
        TalleresContent(
            uiState = TalleresUiState.Initial,
            onVerTaller = {},
            onTalleres = {},
            onMisCoches = {},
            onPerfil = {},
            onReintentar = {}
        )
    }
}

@Preview(showBackground = true, name = "Talleres - Loading")
@Composable
fun TalleresLoadingPreview() {
    RepairLinkAndroidTheme {
        TalleresContent(
            uiState = TalleresUiState.Loading,
            onVerTaller = {},
            onTalleres = {},
            onMisCoches = {},
            onPerfil = {},
            onReintentar = {}
        )
    }
}

@Preview(showBackground = true, name = "Talleres - Empty")
@Composable
fun TalleresEmptyPreview() {
    RepairLinkAndroidTheme {
        TalleresContent(
            uiState = TalleresUiState.Empty,
            onVerTaller = {},
            onTalleres = {},
            onMisCoches = {},
            onPerfil = {},
            onReintentar = {}
        )
    }
}

@Preview(showBackground = true, name = "Talleres - Error")
@Composable
fun TalleresErrorPreview() {
    RepairLinkAndroidTheme {
        TalleresContent(
            uiState = TalleresUiState.Error(
                message = "No se pudieron cargar los talleres"
            ),
            onVerTaller = {},
            onTalleres = {},
            onMisCoches = {},
            onPerfil = {},
            onReintentar = {}
        )
    }
}
