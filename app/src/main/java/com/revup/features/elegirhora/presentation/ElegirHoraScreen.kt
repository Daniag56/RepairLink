package com.revup.features.elegirhora.presentation

import androidx.compose.material3.Icon
import androidx.compose.ui.res.painterResource
import com.revup.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.revup.core.ui.theme.RepairLinkAndroidTheme

@Composable
fun ElegirHoraScreen(
    onVolver: () -> Unit,
    onSolicitarCita: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ElegirHoraViewModel = hiltViewModel()
) {
    val horaElegida by viewModel.horaElegida.collectAsStateWithLifecycle()
    val motivoElegido by viewModel.motivoElegido.collectAsStateWithLifecycle()

    ElegirHoraContent(
        filasHoras = viewModel.filasHoras,
        filasMotivos = viewModel.filasMotivos,
        horaElegida = horaElegida,
        motivoElegido = motivoElegido,
        comentarioState = viewModel.comentarioTextFieldState,
        onElegirHora = { viewModel.onElegirHora(it) },
        onElegirMotivo = { viewModel.onElegirMotivo(it) },
        onVolver = onVolver,
        onSolicitarCita = onSolicitarCita,
        modifier = modifier
    )
}

@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
fun ElegirHoraScreenPreview() {
    RepairLinkAndroidTheme {
        ElegirHoraContent(
            filasHoras = listOf(
                listOf(
                    HoraUi("09:00", true),
                    HoraUi("09:30", false),
                    HoraUi("10:00", true)
                ),
                listOf(
                    HoraUi("10:30", true),
                    HoraUi("11:00", false),
                    HoraUi("11:30", true)
                )
            ),
            filasMotivos = listOf(
                listOf("Revisión", "Frenos", "Aceite"),
                listOf("Neumáticos", "Otro")
            ),
            horaElegida = "10:30",
            motivoElegido = "Revisión",
            comentarioState = TextFieldState(),
            onElegirHora = {},
            onElegirMotivo = {},
            onVolver = {},
            onSolicitarCita = {}
        )
    }
}

@Composable
fun ElegirHoraContent(
    filasHoras: List<List<HoraUi>>,
    filasMotivos: List<List<String>>,
    horaElegida: String,
    motivoElegido: String,
    comentarioState: TextFieldState,
    onElegirHora: (String) -> Unit,
    onElegirMotivo: (String) -> Unit,
    onVolver: () -> Unit,
    onSolicitarCita: () -> Unit,
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
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = { onVolver() }) {
                Icon(painter = painterResource(R.drawable.ic_left), contentDescription = null)
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "Jueves 8 de octubre", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text(text = "Talleres Hermanos Ruiz", fontSize = 12.sp)
            }
            Text(text = "6 libres", fontSize = 12.sp, fontWeight = FontWeight.Bold)
        }

        Text(text = "Elige una hora", fontSize = 17.sp, fontWeight = FontWeight.Bold)

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            filasHoras.forEach { fila ->
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    fila.forEach { hora ->
                        BotonHora(
                            hora = hora,
                            elegida = hora.hora == horaElegida,
                            onClick = { onElegirHora(hora.hora) },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }

        Text(text = "Motivo de la visita", fontSize = 13.sp, fontWeight = FontWeight.Bold)

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            filasMotivos.forEach { fila ->
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    fila.forEach { motivo ->
                        if (motivo == motivoElegido) {
                            Button(onClick = { onElegirMotivo(motivo) }) { Text(text = motivo) }
                        } else {
                            OutlinedButton(onClick = { onElegirMotivo(motivo) }) { Text(text = motivo) }
                        }
                    }
                }
            }
        }

        Text(text = "Vehículo", fontSize = 13.sp, fontWeight = FontWeight.Bold)
        Card(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Renault Clio 3 · 4821 KLM   ⌄",
                fontSize = 16.sp,
                modifier = Modifier.padding(16.dp)
            )
        }

        Text(text = "Comentario para el taller (opcional)", fontSize = 13.sp, fontWeight = FontWeight.Bold)
        OutlinedTextField(
            state = comentarioState,
            placeholder = { Text(text = "Ej.: chirrían los frenos al parar", fontSize = 14.sp) },
            lineLimits = TextFieldLineLimits.SingleLine,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { onSolicitarCita() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Solicitar cita · $horaElegida", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun BotonHora(
    hora: HoraUi,
    elegida: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (elegida) {
        Button(onClick = { onClick() }, modifier = modifier) {
            Text(text = hora.hora, fontWeight = FontWeight.Bold)
        }
    } else {
        OutlinedButton(onClick = { onClick() }, enabled = hora.libre, modifier = modifier) {
            Text(text = hora.hora)
        }
    }
}
