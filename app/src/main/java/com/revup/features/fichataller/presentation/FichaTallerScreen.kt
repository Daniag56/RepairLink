package com.revup.features.fichataller.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.revup.R
import com.revup.core.ui.theme.RepairLinkColors

@Composable
fun FichaTallerScreen(
    onVolver: () -> Unit,
    onVerHoras: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: FichaTallerViewModel = hiltViewModel()
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(RepairLinkColors.Bg)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 20.dp)
                .padding(top = 8.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Barra de arriba: botón atrás y nombre del taller
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { onVolver() },
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(RepairLinkColors.White)
                        .border(1.dp, RepairLinkColors.Line, CircleShape)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_left),
                        contentDescription = "Volver",
                        tint = RepairLinkColors.Ink,
                        modifier = Modifier.size(20.dp)
                    )
                }
                Text(
                    text = "Talleres Hermanos Ruiz",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = RepairLinkColors.Ink
                )
            }

            // Tarjeta con los datos del taller
            TarjetaTaller()

            // Título "Elige un día" y el mes
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Elige un día",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = RepairLinkColors.Ink
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "‹", fontSize = 20.sp, color = RepairLinkColors.Ink)
                    Text(
                        text = "Octubre 2026",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = RepairLinkColors.Ink
                    )
                    Text(text = "›", fontSize = 20.sp, color = RepairLinkColors.Ink)
                }
            }

            // El calendario
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = RepairLinkColors.White),
                border = BorderStroke(1.dp, RepairLinkColors.Line)
            ) {
                Column(
                    modifier = Modifier.padding(13.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    // Letras de los días de la semana
                    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        listOf("L", "M", "X", "J", "V", "S", "D").forEach { letra ->
                            Text(
                                text = letra,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium,
                                color = RepairLinkColors.Muted,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(vertical = 4.dp)
                            )
                        }
                    }

                    // Una fila por semana: chunked(7) parte la lista de 35 días en 5 listas de 7
                    viewModel.dias.chunked(7).forEach { semana ->
                        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                            semana.forEach { dia ->
                                CasillaDia(dia = dia, modifier = Modifier.weight(1f))
                            }
                        }
                    }
                }
            }

            // Leyenda de colores
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally)
            ) {
                ElementoLeyenda(color = RepairLinkColors.TealSoft, texto = "Libre")
                ElementoLeyenda(color = RepairLinkColors.AccentSoft, texto = "Pocas horas")
                ElementoLeyenda(color = RepairLinkColors.Line, texto = "Completo")
            }
        }

        // Barra de abajo: día elegido y botón "Ver horas"
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(RepairLinkColors.White)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(RepairLinkColors.Line)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                    Text(
                        text = "Jueves 8 de octubre",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = RepairLinkColors.Ink
                    )
                    Text(
                        text = "6 horas libres",
                        fontSize = 13.sp,
                        color = RepairLinkColors.Success
                    )
                }
                Button(
                    onClick = { onVerHoras() },
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = RepairLinkColors.PetrolBlue),
                    contentPadding = PaddingValues(horizontal = 22.dp, vertical = 14.dp)
                ) {
                    Text(
                        text = "Ver horas",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = RepairLinkColors.White
                    )
                }
            }
        }
    }
}

@Composable
fun TarjetaTaller(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = RepairLinkColors.White),
        border = BorderStroke(1.dp, RepairLinkColors.Line)
    ) {
        Column(
            modifier = Modifier.padding(15.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Logo redondo con la llave
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .clip(CircleShape)
                        .background(RepairLinkColors.PetrolBlue),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_wrench),
                        contentDescription = null,
                        tint = RepairLinkColors.White,
                        modifier = Modifier.size(26.dp)
                    )
                }

                Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
                    Text(
                        text = "Abierto · cierra a las 19:00",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = RepairLinkColors.Success
                    )
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "★", fontSize = 13.sp, color = RepairLinkColors.OrangePrimary)
                        Text(
                            text = "4,9",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = RepairLinkColors.Ink
                        )
                        Text(text = "(312) ·", fontSize = 13.sp, color = RepairLinkColors.Muted)
                        Icon(
                            painter = painterResource(R.drawable.ic_pin),
                            contentDescription = null,
                            tint = RepairLinkColors.Muted,
                            modifier = Modifier.size(13.dp)
                        )
                        Text(text = "0,8 km", fontSize = 13.sp, color = RepairLinkColors.Muted)
                    }
                    Text(
                        text = "C/ Ejemplo 12 · Taller multimarca",
                        fontSize = 13.sp,
                        color = RepairLinkColors.Muted
                    )
                }
            }

            // Chips de servicios
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                listOf("Mecánica", "Frenos", "ITV", "Aceite").forEach { servicio ->
                    Text(
                        text = servicio,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        color = RepairLinkColors.Secondary,
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .background(RepairLinkColors.TealSoft)
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun CasillaDia(
    dia: DiaUi,
    modifier: Modifier = Modifier
) {
    val fondo = when (dia.estado) {
        "Libre", "Hoy" -> RepairLinkColors.TealSoft
        "Pocas horas" -> RepairLinkColors.AccentSoft
        "Completo" -> RepairLinkColors.Line
        "Seleccionado" -> RepairLinkColors.PetrolBlue
        else -> Color.Transparent
    }
    val colorTexto = when (dia.estado) {
        "Pocas horas" -> RepairLinkColors.AccentDark
        "Completo" -> RepairLinkColors.Muted
        "Seleccionado" -> RepairLinkColors.White
        "No disponible" -> RepairLinkColors.LightGrayText
        else -> RepairLinkColors.PetrolBlue
    }
    // Solo el día de hoy lleva borde; el resto lo lleva transparente
    val colorBorde = if (dia.estado == "Hoy") RepairLinkColors.PetrolBlue else Color.Transparent

    Box(
        modifier = modifier
            .height(42.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(fondo)
            .border(1.5.dp, colorBorde, RoundedCornerShape(10.dp)),
        contentAlignment = Alignment.Center
    ) {
        if (dia.estado != "Vacío") {
            Text(
                text = dia.numero.toString(),
                fontSize = 14.sp,
                fontWeight = if (dia.estado == "No disponible") FontWeight.Normal else FontWeight.SemiBold,
                color = colorTexto,
                textDecoration = if (dia.estado == "Completo") TextDecoration.LineThrough else TextDecoration.None
            )
        }
    }
}

@Composable
fun ElementoLeyenda(color: Color, texto: String) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(12.dp)
                .clip(RoundedCornerShape(3.dp))
                .background(color)
        )
        Text(text = texto, fontSize = 12.sp, color = RepairLinkColors.Muted)
    }
}
