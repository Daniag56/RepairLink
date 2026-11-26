package com.revup.features.miscitas.presentation

import androidx.compose.material3.MaterialTheme
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.revup.core.ui.components.BarraInferior
import com.revup.core.ui.theme.RepairLinkAndroidTheme
import com.revup.core.ui.theme.RepairLinkColors

private data class CitaUi(
    val dia: String,
    val numero: String,
    val hora: String,
    val taller: String,
    val motivo: String,
    val coche: String,
    val estado: String,
    val accion: String
)

private val citasProximas = listOf(
    CitaUi(
        dia = "JUE",
        numero = "8",
        hora = "10:30",
        taller = "Talleres Hermanos Ruiz",
        motivo = "Revisión",
        coche = "Renault Clio 3",
        estado = "Pendiente de confirmar",
        accion = "Cancelar"
    ),
    CitaUi(
        dia = "LUN",
        numero = "12",
        hora = "16:30",
        taller = "AutoService Costa",
        motivo = "Revisión",
        coche = "SEAT León",
        estado = "Confirmada",
        accion = "Cómo llegar"
    )
)

private val citasPasadas = listOf(
    CitaUi(
        dia = "VIE",
        numero = "2",
        hora = "09:00",
        taller = "Neumáticos y Frenos Delta",
        motivo = "Neumáticos",
        coche = "Peugeot 208",
        estado = "Completada",
        accion = "Valorar"
    )
)

@Composable
fun MisCitasScreen(
    onVolver: () -> Unit = {},
    onTalleres: () -> Unit,
    onMisCoches: () -> Unit,
    onPerfil: () -> Unit,
    modifier: Modifier = Modifier
) {
    MisCitasContent(
        onVolver = onVolver,
        onTalleres = onTalleres,
        onMisCoches = onMisCoches,
        onPerfil = onPerfil,
        modifier = modifier
    )
}

@Composable
fun MisCitasContent(
    onVolver: () -> Unit,
    onTalleres: () -> Unit,
    onMisCoches: () -> Unit,
    onPerfil: () -> Unit,
    modifier: Modifier = Modifier
) {
    var mostrarProximas by remember {
        mutableStateOf(true)
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            BarraInferior(
                seleccionada = "Mis coches",
                onTalleres = onTalleres,
                onMisCoches = onMisCoches,
                onPerfil = onPerfil
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(horizontal = 20.dp)
        ) {

            // Cabecera

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .background(
                            color = Color.White,
                            shape = CircleShape
                        )
                        .clickable { onVolver() },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "‹",
                        fontSize = 32.sp
                    )
                }

                Text(
                    text = "Mis citas",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            // Selector Próximas / Pasadas

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(4.dp)
            ) {
                TabCitas(
                    texto = "Próximas",
                    seleccionada = mostrarProximas,
                    modifier = Modifier.weight(1f),
                    onClick = { mostrarProximas = true }
                )

                TabCitas(
                    texto = "Pasadas",
                    seleccionada = !mostrarProximas,
                    modifier = Modifier.weight(1f),
                    onClick = { mostrarProximas = false }
                )
            }

            Spacer(modifier = Modifier.size(16.dp))

            // Citas

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                if (mostrarProximas) {
                    items(citasProximas) { cita ->
                        CitaCard(cita = cita)
                    }
                } else {
                    items(citasPasadas) { cita ->
                        CitaCard(cita = cita)
                    }
                }
            }
        }
    }
}

@Composable
private fun TabCitas(
    texto: String,
    seleccionada: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .background(
                color = if (seleccionada) {
                    Color.White
                } else {
                    Color.Transparent
                },
                shape = RoundedCornerShape(8.dp)
            )
            .clickable { onClick() }
            .padding(vertical = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = texto,
            fontSize = 14.sp,
            fontWeight = if (seleccionada) {
                FontWeight.SemiBold
            } else {
                FontWeight.Normal
            }
        )
    }
}

@Composable
private fun CitaCard(
    cita: CitaUi
) {
    val colorEstado = when (cita.estado) {
        "Pendiente de confirmar" -> RepairLinkColors.OrangePrimary
        "Confirmada" -> RepairLinkColors.Success
        else -> MaterialTheme.colorScheme.surfaceVariant
    }

    val colorTextoEstado = when (cita.estado) {
        "Pendiente de confirmar" -> RepairLinkColors.Black
        "Confirmada" -> RepairLinkColors.White
        else -> MaterialTheme.colorScheme.onSurfaceVariant
    }

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            // Bloque de fecha

            Column(
                modifier = Modifier
                    .size(width = 52.dp, height = 60.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(8.dp)
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = cita.dia,
                    color = RepairLinkColors.White,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Medium
                )

                Text(
                    text = cita.numero,
                    color = RepairLinkColors.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            // Información de la cita

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = "${cita.hora} · ${cita.taller}",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "${cita.motivo} · ${cita.coche}",
                    fontSize = 11.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .background(
                                color = colorEstado,
                                shape = RoundedCornerShape(50.dp)
                            )
                            .padding(
                                horizontal = 10.dp,
                                vertical = 5.dp
                            )
                    ) {
                        Text(
                            text = cita.estado,
                            fontSize = 10.sp,
                            color = colorTextoEstado,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    Text(
                        text = cita.accion,
                        fontSize = 11.sp,
                        color = if (cita.accion == "Cancelar") {
                            MaterialTheme.colorScheme.error
                        } else {
                            MaterialTheme.colorScheme.primary
                        },
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.clickable { }
                    )
                }
            }
        }
    }
}

// Preview

@Preview(
    showBackground = true,
    widthDp = 390,
    heightDp = 844
)
@Composable
fun MisCitasScreenPreview() {
    RepairLinkAndroidTheme {
        MisCitasContent(
            onVolver = {},
            onTalleres = {},
            onMisCoches = {},
            onPerfil = {}
        )
    }
}
