package com.revup.features.miscoches.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.revup.core.ui.components.BarraInferior
import com.revup.core.ui.theme.RepairLinkAndroidTheme

@Composable
fun MisCochesScreen(
    onAnadirCoche: () -> Unit,
    onVerTaller: () -> Unit,
    onTalleres: () -> Unit,
    onPerfil: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MisCochesViewModel = hiltViewModel()
) {
    MisCochesContent(
        coches = viewModel.coches,
        onAnadirCoche = onAnadirCoche,
        onVerTaller = onVerTaller,
        onTalleres = onTalleres,
        onPerfil = onPerfil,
        modifier = modifier
    )
}

@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
fun MisCochesScreenPreview() {
    RepairLinkAndroidTheme {
        MisCochesContent(
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
            ),
            onAnadirCoche = {},
            onVerTaller = {},
            onTalleres = {},
            onPerfil = {}
        )
    }
}

@Composable
fun MisCochesContent(
    coches: List<CocheUi>,
    onAnadirCoche: () -> Unit,
    onVerTaller: () -> Unit,
    onTalleres: () -> Unit,
    onPerfil: () -> Unit,
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
                    Text(text = "Mis coches", fontSize = 26.sp, fontWeight = FontWeight.Bold)
                    Text(text = "3 vehículos · 2 en el taller", fontSize = 13.sp)
                }

                Button(onClick = { onAnadirCoche() }) {
                    Text(text = "+ Añadir")
                }
            }

            Card(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "✓", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Text(
                        text = "Cita solicitada: Jueves 8 oct, 10:30 en Talleres Hermanos Ruiz",
                        fontSize = 13.sp,
                        modifier = Modifier.weight(1f)
                    )
                }
                Button(
                    onClick = { onVerTaller() },
                    modifier = Modifier.padding(start = 16.dp, bottom = 16.dp)
                ) {
                    Text(text = "Ver taller", fontSize = 12.sp)
                }
            }

            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(coches, key = { it.id }) { coche ->
                    TarjetaCoche(coche = coche)
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
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(text = "🚗 ${coche.nombre}", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Text(text = coche.matricula, fontSize = 12.sp)
                }
                Text(text = coche.estado, fontSize = 12.sp, fontWeight = FontWeight.Bold)
            }

            Text(text = "🔧 ${coche.taller}", fontSize = 13.sp)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = coche.paso, fontSize = 13.sp)
                Text(
                    text = "${coche.pasoActual}/${coche.pasosTotales}",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
