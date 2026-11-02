package com.revup.features.miscoches.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.revup.R
import com.revup.core.ui.theme.RepairLinkColors

@Composable
fun MisCochesScreen(
    onAnadirCoche: () -> Unit,
    onVerTaller: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MisCochesViewModel = hiltViewModel()
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(RepairLinkColors.Bg)
            .padding(horizontal = 20.dp)
            .padding(top = 8.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Cabecera: título a la izquierda y botón "Añadir" a la derecha
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                Text(
                    text = "Mis coches",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = RepairLinkColors.Ink
                )
                Text(
                    text = "3 vehículos · 2 en el taller",
                    fontSize = 13.sp,
                    color = RepairLinkColors.Muted
                )
            }

            Button(
                onClick = { onAnadirCoche() },
                colors = ButtonDefaults.buttonColors(containerColor = RepairLinkColors.PetrolBlue),
                contentPadding = PaddingValues(horizontal = 14.dp, vertical = 9.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_plus),
                    contentDescription = null,
                    tint = RepairLinkColors.White,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Añadir",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = RepairLinkColors.White
                )
            }
        }

        // Banda verde de cita solicitada
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .clickable { onVerTaller() }
                .background(RepairLinkColors.SuccessSoft)
                .padding(12.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "✓",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = RepairLinkColors.Success
            )
            Text(
                text = "Cita solicitada: Jueves 8 oct, 10:30 en Talleres Hermanos Ruiz",
                fontSize = 13.sp,
                color = RepairLinkColors.Ink
            )
        }

        // Lista de coches
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(14.dp),
            contentPadding = PaddingValues(bottom = 14.dp)
        ) {
            items(viewModel.coches, key = { it.id }) { coche ->
                TarjetaCoche(coche = coche)
            }
        }
    }
}

@Composable
fun TarjetaCoche(
    coche: CocheUi,
    modifier: Modifier = Modifier
) {
    // Colores según el estado del coche
    val fondoEstado = when (coche.estado) {
        "En reparación" -> RepairLinkColors.AccentSoft
        "En revisión" -> RepairLinkColors.TealSoft
        else -> RepairLinkColors.SuccessSoft
    }
    val textoEstado = when (coche.estado) {
        "En reparación" -> RepairLinkColors.AccentDark
        "En revisión" -> RepairLinkColors.Secondary
        else -> RepairLinkColors.Success
    }
    val colorBarra = when (coche.estado) {
        "En reparación" -> RepairLinkColors.OrangePrimary
        "En revisión" -> RepairLinkColors.Secondary
        else -> RepairLinkColors.Success
    }

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = RepairLinkColors.White),
        border = BorderStroke(1.dp, RepairLinkColors.Line)
    ) {
        Column(
            modifier = Modifier.padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Fila 1: icono, nombre + matrícula y chip de estado
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(RepairLinkColors.TealSoft),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_car),
                        contentDescription = null,
                        tint = RepairLinkColors.Secondary,
                        modifier = Modifier.size(26.dp)
                    )
                }

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = coche.nombre,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = RepairLinkColors.Ink
                    )
                    Text(
                        text = coche.matricula,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = RepairLinkColors.Ink,
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(RepairLinkColors.Bg)
                            .border(1.dp, RepairLinkColors.Line, RoundedCornerShape(6.dp))
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                    )
                }

                Text(
                    text = coche.estado,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = textoEstado,
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .background(fondoEstado)
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                )
            }

            // Fila 2: taller
            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_wrench),
                    contentDescription = null,
                    tint = RepairLinkColors.Muted,
                    modifier = Modifier.size(14.dp)
                )
                Text(
                    text = coche.taller,
                    fontSize = 13.sp,
                    color = RepairLinkColors.Muted
                )
            }

            // Fila 3: paso actual y barra de progreso
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = coche.paso,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium,
                        color = RepairLinkColors.Ink
                    )
                    Text(
                        text = "${coche.pasoActual}/${coche.pasosTotales}",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = RepairLinkColors.Muted
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp)
                        .clip(RoundedCornerShape(3.dp))
                        .background(RepairLinkColors.Line)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(coche.pasoActual / coche.pasosTotales.toFloat())
                            .fillMaxHeight()
                            .background(colorBarra)
                    )
                }
            }
        }
    }
}
