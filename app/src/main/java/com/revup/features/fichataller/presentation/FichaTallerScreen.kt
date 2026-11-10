package com.revup.features.fichataller.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.revup.core.ui.theme.RepairLinkAndroidTheme

@Composable
fun FichaTallerScreen(
    onVolver: () -> Unit,
    onReservarCita: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: FichaTallerViewModel = hiltViewModel()
) {
    FichaTallerContent(
        nombre = viewModel.nombre,
        onVolver = onVolver,
        onReservarCita = onReservarCita,
        modifier = modifier
    )
}

@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
fun FichaTallerScreenPreview() {
    RepairLinkAndroidTheme {
        FichaTallerContent(
            nombre = "Talleres Hermanos Ruiz",
            onVolver = {},
            onReservarCita = {}
        )
    }
}

@Composable
fun FichaTallerContent(
    nombre: String,
    onVolver: () -> Unit,
    onReservarCita: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = { onVolver() }) {
                Text("<", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
            OutlinedButton(onClick = {}) {
                Text("☆", fontSize = 18.sp)
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "🔧", fontSize = 32.sp)
            Column {
                Text(text = nombre, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                Text(text = "Abierto · cierra a las 19:00", fontSize = 13.sp)
            }
        }

        Text(text = "⭐ 4,9 (312 opiniones) · 0,8 km · C/ Ejemplo 12", fontSize = 13.sp)

        Text(text = "Mecánica   Frenos   ITV   Aceite", fontSize = 12.sp, fontWeight = FontWeight.Bold)

        Card(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "-15%", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Column {
                    Text(text = "Cambio de aceite", fontSize = 15.sp, fontWeight = FontWeight.Bold)
                    Text(text = "Aceite y filtro · hasta el 30 nov", fontSize = 12.sp)
                }
            }
        }

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(text = "Horario", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                FilaHorario(dias = "Lunes – Viernes", horas = "09:00–14:00 · 16:00–19:00")
                FilaHorario(dias = "Sábado", horas = "09:00–14:00")
                FilaHorario(dias = "Domingo", horas = "Cerrado")
            }
        }

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Opiniones", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Text(text = "Ver todas", fontSize = 13.sp, fontWeight = FontWeight.Bold)
                }
                Text(text = "⭐⭐⭐⭐⭐", fontSize = 13.sp)
                Text(text = "«Rápidos y muy claros con el presupuesto.» · Carlos M.", fontSize = 13.sp)
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedButton(onClick = {}) {
                Text("💬")
            }
            Button(
                onClick = { onReservarCita() },
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Reservar cita", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun FilaHorario(
    dias: String,
    horas: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = dias, fontSize = 13.sp)
        Text(text = horas, fontSize = 13.sp, fontWeight = FontWeight.Bold)
    }
}
