package com.revup.features.elegirdia.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.revup.core.ui.theme.RepairLinkAndroidTheme

@Composable
fun ElegirDiaScreen(
    onVolver: () -> Unit,
    onVerHoras: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ElegirDiaViewModel = hiltViewModel()
) {
    ElegirDiaContent(
        semanas = viewModel.semanas,
        onVolver = onVolver,
        onVerHoras = onVerHoras,
        modifier = modifier
    )
}

@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
fun ElegirDiaScreenPreview() {
    RepairLinkAndroidTheme {
        ElegirDiaContent(
            semanas = listOf(
                listOf(
                    DiaUi(0, "Vacío"),
                    DiaUi(0, "Vacío"),
                    DiaUi(0, "Vacío"),
                    DiaUi(1, "No disponible"),
                    DiaUi(2, "No disponible"),
                    DiaUi(3, "No disponible"),
                    DiaUi(4, "No disponible")
                ),
                listOf(
                    DiaUi(5, "No disponible"),
                    DiaUi(6, "Hoy"),
                    DiaUi(7, "Completo"),
                    DiaUi(8, "Seleccionado"),
                    DiaUi(9, "Pocas horas"),
                    DiaUi(10, "Libre"),
                    DiaUi(11, "No disponible")
                )
            ),
            onVolver = {},
            onVerHoras = {}
        )
    }
}

@Composable
fun ElegirDiaContent(
    semanas: List<List<DiaUi>>,
    onVolver: () -> Unit,
    onVerHoras: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = { onVolver() }) {
                Text("<", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
            Text(text = "Talleres Hermanos Ruiz", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(text = "Abierto · cierra a las 19:00", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                Text(text = "⭐ 4,9 (312) · 0,8 km", fontSize = 13.sp)
                Text(text = "C/ Ejemplo 12 · Taller multimarca", fontSize = 13.sp)
                Text(text = "Mecánica   Frenos   ITV   Aceite", fontSize = 12.sp)
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Elige un día", fontSize = 17.sp, fontWeight = FontWeight.Bold)
            Text(text = "‹  Octubre 2026  ›", fontSize = 14.sp, fontWeight = FontWeight.Bold)
        }

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row {
                    listOf("L", "M", "X", "J", "V", "S", "D").forEach { letra ->
                        Text(
                            text = letra,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }

                semanas.forEach { semana ->
                    Row {
                        semana.forEach { dia ->
                            CasillaDia(dia = dia, modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        }

        Text(text = "• = pocas horas   ✕ = completo", fontSize = 12.sp)

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(text = "Jueves 8 de octubre", fontSize = 15.sp, fontWeight = FontWeight.Bold)
                Text(text = "6 horas libres", fontSize = 13.sp)
            }
            Button(onClick = { onVerHoras() }) {
                Text(text = "Ver horas", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun CasillaDia(
    dia: DiaUi,
    modifier: Modifier = Modifier
) {
    val texto = when (dia.estado) {
        "Vacío" -> ""
        "Pocas horas" -> "${dia.numero}•"
        "Completo" -> "${dia.numero}✕"
        "Seleccionado" -> "[${dia.numero}]"
        else -> dia.numero.toString()
    }

    Text(
        text = texto,
        fontSize = 14.sp,
        fontWeight = if (dia.estado == "Seleccionado" || dia.estado == "Hoy") FontWeight.Bold else FontWeight.Normal,
        textAlign = TextAlign.Center,
        modifier = modifier.padding(vertical = 8.dp)
    )
}
