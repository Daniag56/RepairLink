package com.revup.features.miscoches.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
fun MisCochesScreen(
    onAnadirCoche: () -> Unit,
    onVerCoche: () -> Unit,
    onTalleres: () -> Unit,
    onPerfil: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MisCochesViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    MisCochesContent(
        uiState = uiState,
        onAnadirCoche = onAnadirCoche,
        onVerCoche = onVerCoche,
        onTalleres = onTalleres,
        onPerfil = onPerfil,
        onReintentar = { viewModel.cargarCoches() },
        modifier = modifier
    )
}

@Composable
fun MisCochesContent(
    uiState: MisCochesUiState,
    onAnadirCoche: () -> Unit,
    onVerCoche: () -> Unit,
    onTalleres: () -> Unit,
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
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Mis coches",
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "3 vehículos · 2 en el taller",
                        fontSize = 13.sp
                    )
                }

                Button(onClick = onAnadirCoche) {
                    Icon(
                        painter = painterResource(R.drawable.ic_plus),
                        contentDescription = null
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    Text(text = "Añadir")
                }
            }

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "✓",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "Cita solicitada: Jueves 8 oct, 10:30 en Talleres Hermanos Ruiz",
                        fontSize = 13.sp,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                when (uiState) {

                    MisCochesUiState.Initial,
                    MisCochesUiState.Loading -> {
                        CircularProgressIndicator()
                    }

                    is MisCochesUiState.Success -> {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            items(
                                uiState.coches,
                                key = { it.id }
                            ) { coche ->
                                TarjetaCoche(
                                    coche = coche,
                                    onVerEstado = onVerCoche
                                )
                            }
                        }
                    }

                    MisCochesUiState.Empty -> {
                        Text(
                            text = "No tienes coches registrados"
                        )
                    }

                    is MisCochesUiState.Error -> {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = uiState.message
                            )

                            Button(
                                onClick = onReintentar
                            ) {
                                Text(text = "Reintentar")
                            }
                        }
                    }
                }
            }
        }

        BarraInferior(
            seleccionada = "Mis coches",
            onTalleres = onTalleres,
            onMisCoches = {},
            onPerfil = onPerfil
        )
    }
}

@Composable
fun TarjetaCoche(
    coche: CocheUi,
    onVerEstado: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_car),
                        contentDescription = null
                    )

                    Column {
                        Text(
                            text = coche.nombre,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = coche.matricula,
                            fontSize = 12.sp
                        )
                    }
                }

                Text(
                    text = coche.estado,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_wrench),
                    contentDescription = null
                )

                Text(
                    text = coche.taller,
                    fontSize = 13.sp
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = coche.paso,
                    fontSize = 13.sp
                )

                Text(
                    text = "${coche.pasoActual}/${coche.pasosTotales}",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Button(
                onClick = onVerEstado
            ) {
                Text(
                    text = "Ver estado",
                    fontSize = 12.sp
                )
            }
        }
    }
}

// Preview de Success: conservamos la Preview original

@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
fun MisCochesScreenPreview() {
    RepairLinkAndroidTheme {
        MisCochesContent(
            uiState = MisCochesUiState.Success(
                coches = listOf(
                    CocheUi(
                        id = 1,
                        nombre = "Renault Clio 3",
                        matricula = "4821 KLM",
                        taller = "Talleres Hermanos Ruiz",
                        estado = "En reparación",
                        paso = "Cambio de pastillas y discos",
                        pasoActual = 5,
                        pasosTotales = 6
                    )
                )
            ),
            onAnadirCoche = {},
            onVerCoche = {},
            onTalleres = {},
            onPerfil = {},
            onReintentar = {}
        )
    }
}

// Preview de Initial

@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
fun MisCochesInitialPreview() {
    RepairLinkAndroidTheme {
        MisCochesContent(
            uiState = MisCochesUiState.Initial,
            onAnadirCoche = {},
            onVerCoche = {},
            onTalleres = {},
            onPerfil = {},
            onReintentar = {}
        )
    }
}

// Preview de Loading

@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
fun MisCochesLoadingPreview() {
    RepairLinkAndroidTheme {
        MisCochesContent(
            uiState = MisCochesUiState.Loading,
            onAnadirCoche = {},
            onVerCoche = {},
            onTalleres = {},
            onPerfil = {},
            onReintentar = {}
        )
    }
}

// Preview de Empty

@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
fun MisCochesEmptyPreview() {
    RepairLinkAndroidTheme {
        MisCochesContent(
            uiState = MisCochesUiState.Empty,
            onAnadirCoche = {},
            onVerCoche = {},
            onTalleres = {},
            onPerfil = {},
            onReintentar = {}
        )
    }
}

// Preview de Error

@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
fun MisCochesErrorPreview() {
    RepairLinkAndroidTheme {
        MisCochesContent(
            uiState = MisCochesUiState.Error(
                message = "Ha ocurrido un error al cargar los coches"
            ),
            onAnadirCoche = {},
            onVerCoche = {},
            onTalleres = {},
            onPerfil = {},
            onReintentar = {}
        )
    }
}
