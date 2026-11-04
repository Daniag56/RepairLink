package com.revup.features.reparaciones.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ReparacionesScreen(
    onTalleres: () -> Unit,
    onMisCoches: () -> Unit,
    onPerfil: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Mis coches",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold
                )

                Button(onClick = {}) {
                    Text("+ Añadir")
                }
            }

            Text(
                text = "1 vehículos · 0 en el taller",
                fontSize = 13.sp
            )

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "✓ Cita solicitada: jueves 8 oct, 10:30 en Talleres Hermanos Ruíz",
                    modifier = Modifier.padding(16.dp),
                    fontSize = 13.sp
                )
            }

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "🚗 Renault Clio 3",
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "En reparación",
                            fontSize = 12.sp
                        )
                    }

                    Text(
                        text = "4821 KLM",
                        fontSize = 11.sp
                    )

                    Text(
                        text = "🔧 Talleres Hermanos Ruiz",
                        fontSize = 12.sp
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Cambio de pastillas y discos",
                            fontSize = 12.sp
                        )

                        Text(
                            text = "5/6",
                            fontSize = 12.sp
                        )
                    }

                    LinearProgressIndicator(
                        progress = { 0.75f },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }


        Spacer(modifier = Modifier.weight(1f))


        NavigationBar {
            NavigationBarItem(
                selected = false,
                onClick = { onTalleres() },
                icon = { Text("🔧") },
                label = { Text("Talleres") }
            )

            NavigationBarItem(
                selected = true,
                onClick = { onMisCoches() },
                icon = { Text("🚗") },
                label = { Text("Mis coches") }
            )

            NavigationBarItem(
                selected = false,
                onClick = { onPerfil() },
                icon = { Text("👤") },
                label = { Text("Perfil") }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ReparacionesScreenPreview() {
    ReparacionesScreen(
        onTalleres = {},
        onMisCoches = {},
        onPerfil = {}
    )
}