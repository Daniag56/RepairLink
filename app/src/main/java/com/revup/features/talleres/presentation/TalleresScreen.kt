package com.revup.features.talleres.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.revup.core.ui.components.BarraInferior

@Composable
fun TalleresScreen(
    onTalleres: () -> Unit,
    onMisCoches: () -> Unit,
    onPerfil: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {

        // CONTENIDO DE LA PANTALLA
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Text(
                text = "Talleres cerca de ti",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            )

            // BUSCADOR
            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "🔍 Buscar taller o servicio",
                    modifier = Modifier.padding(16.dp),
                    fontSize = 14.sp
                )
            }

            // FILTROS
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


            Card(
                modifier = Modifier.fillMaxWidth()
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
                        Text(
                            text = "⭐ Destacado",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text("🔧", fontSize = 26.sp)
                    }

                    Text(
                        text = "Talleres Hermanos Ruiz",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "⭐ 4,9 (312) · 0,8 km · C/ Ejemplo 12",
                        fontSize = 12.sp
                    )

                    Text(
                        text = "Mecánica   Frenos   ITV   Aceite",
                        fontSize = 12.sp
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "🕒 Hoy 16:30",
                            fontSize = 12.sp
                        )

                        Button(onClick = {}) {
                            Text(
                                text = "Ver calendario",
                                fontSize = 11.sp
                            )
                        }
                    }
                }
            }


            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "🔧",
                        fontSize = 28.sp
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(
                            text = "AutoService Costa",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "⭐ 4,8 (204) · 1,2 km",
                            fontSize = 12.sp
                        )

                        Text(
                            text = "🕒 Mañana · 09:00",
                            fontSize = 12.sp
                        )
                    }

                    Text(
                        text = "➜",
                        fontSize = 24.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))


        BarraInferior(
            seleccionada = "Talleres",
            onTalleres = onTalleres,
            onMisCoches = onMisCoches,
            onPerfil = onPerfil
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TalleresScreenPreview() {
    TalleresScreen(
        onTalleres = {},
        onMisCoches = {},
        onPerfil = {}
    )
}