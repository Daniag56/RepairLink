package com.revup.features.citasolicitada.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedButton
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
fun CitaSolicitadaScreen(
    onVerMisCitas: () -> Unit,
    onVolverInicio: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CitaSolicitadaViewModel = hiltViewModel()
) {
    CitaSolicitadaContent(
        taller = viewModel.taller,
        dia = viewModel.dia,
        hora = viewModel.hora,
        vehiculo = viewModel.vehiculo,
        motivo = viewModel.motivo,
        onVerMisCitas = onVerMisCitas,
        onVolverInicio = onVolverInicio,
        modifier = modifier
    )
}

@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
fun CitaSolicitadaScreenPreview() {
    RepairLinkAndroidTheme {
        CitaSolicitadaContent(
            taller = "Talleres Hermanos Ruiz",
            dia = "Jueves 8 de octubre",
            hora = "10:30",
            vehiculo = "Renault Clio 3",
            motivo = "Revisión",
            onVerMisCitas = {},
            onVolverInicio = {}
        )
    }
}

@Composable
fun CitaSolicitadaContent(
    taller: String,
    dia: String,
    hora: String,
    vehiculo: String,
    motivo: String,
    onVerMisCitas: () -> Unit,
    onVolverInicio: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Spacer(modifier = Modifier.height(40.dp))

        Text(text = "✓", fontSize = 48.sp, fontWeight = FontWeight.Bold)

        Text(text = "¡Cita solicitada!", fontSize = 26.sp, fontWeight = FontWeight.Bold)

        Text(
            text = "$taller confirmará tu cita en breve. Te avisaremos con una notificación.",
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                FilaDato(nombre = "Taller", valor = taller)
                FilaDato(nombre = "Día", valor = dia)
                FilaDato(nombre = "Hora", valor = hora)
                FilaDato(nombre = "Vehículo", valor = vehiculo)
                FilaDato(nombre = "Motivo", valor = motivo)
                Text(text = "Pendiente de confirmar", fontSize = 12.sp, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { onVerMisCitas() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Ver mis citas", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }

        OutlinedButton(
            onClick = { onVolverInicio() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Volver al inicio", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun FilaDato(
    nombre: String,
    valor: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = nombre, fontSize = 14.sp)
        Text(text = valor, fontSize = 14.sp, fontWeight = FontWeight.Bold)
    }
}
