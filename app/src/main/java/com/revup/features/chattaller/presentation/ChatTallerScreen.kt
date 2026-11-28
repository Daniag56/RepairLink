package com.revup.features.chattaller.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.revup.core.ui.theme.RepairLinkAndroidTheme
import com.revup.core.ui.theme.RepairLinkColors

@Composable
fun ChatTallerScreen(
    modifier: Modifier = Modifier,
    onVolver: () -> Unit = {}
) {
    var mensajeEscrito by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(RepairLinkColors.Bg)
    ) {

        // Cabecera
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(RepairLinkColors.White)
                .padding(16.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .clickable { onVolver() }
                        .background(RepairLinkColors.Bg, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "‹",
                        fontSize = 32.sp,
                        color = RepairLinkColors.Black
                    )
                }

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 12.dp)
                ) {
                    Text(
                        text = "Renault Clio 3",
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Bold,
                        color = RepairLinkColors.PetrolBlue
                    )

                    Text(
                        text = "Talleres Hermanos Ruiz · En línea",
                        fontSize = 12.sp,
                        color = RepairLinkColors.Muted
                    )
                }

                Box(
                    modifier = Modifier
                        .background(
                            RepairLinkColors.AccentSoft,
                            RoundedCornerShape(20.dp)
                        )
                        .padding(horizontal = 10.dp, vertical = 7.dp)
                ) {
                    Text(
                        text = "En reparación",
                        fontSize = 11.sp,
                        color = RepairLinkColors.OrangePrimary,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Pestañas Chat y Estado
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        RepairLinkColors.TealSoft,
                        RoundedCornerShape(12.dp)
                    )
                    .padding(4.dp)
            ) {

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .background(
                            RepairLinkColors.White,
                            RoundedCornerShape(10.dp)
                        )
                        .padding(10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Chat",
                        fontWeight = FontWeight.SemiBold,
                        color = RepairLinkColors.PetrolBlue
                    )
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Estado",
                        color = RepairLinkColors.Muted
                    )
                }
            }
        }

        // Conversación
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Hoy",
                        modifier = Modifier
                            .background(
                                RepairLinkColors.TealSoft,
                                RoundedCornerShape(20.dp)
                            )
                            .padding(horizontal = 16.dp, vertical = 6.dp),
                        fontSize = 12.sp,
                        color = RepairLinkColors.PetrolBlue
                    )
                }
            }

            // Primer mensaje del taller
            item {
                MensajeTaller(
                    texto = "Hemos terminado la revisión. Los discos delanteros están al límite; te recomendamos cambiarlos junto con las pastillas.",
                    hora = "12:12"
                )
            }

            // Presupuesto
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .background(
                            RepairLinkColors.White,
                            RoundedCornerShape(14.dp)
                        )
                        .padding(14.dp)
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Presupuesto",
                            fontSize = 12.sp,
                            color = RepairLinkColors.Muted,
                            modifier = Modifier.weight(1f)
                        )

                        Box(
                            modifier = Modifier
                                .background(
                                    RepairLinkColors.SuccessSoft,
                                    RoundedCornerShape(20.dp)
                                )
                                .padding(horizontal = 9.dp, vertical = 5.dp)
                        ) {
                            Text(
                                text = "✓ Aceptado",
                                fontSize = 11.sp,
                                color = RepairLinkColors.Success
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Pastillas y discos delanteros",
                        fontSize = 14.sp,
                        color = RepairLinkColors.Ink
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "186,40 €",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = RepairLinkColors.PetrolBlue
                    )
                }
            }

            // Mensaje del cliente
            item {
                MensajeCliente(
                    texto = "Vale, adelante con el cambio. ¿Para cuándo estaría?",
                    hora = "12:50"
                )
            }

            // Respuesta del taller
            item {
                MensajeTaller(
                    texto = "Hoy a las 18:00 lo tienes listo.",
                    hora = "12:52"
                )
            }

            // Último mensaje del cliente
            item {
                MensajeCliente(
                    texto = "Perfecto, gracias.",
                    hora = "12:53"
                )
            }
        }

        // Barra inferior para escribir
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(RepairLinkColors.White)
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            Box(
                modifier = Modifier
                    .size(42.dp)
                    .background(
                        RepairLinkColors.TealSoft,
                        RoundedCornerShape(12.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "+",
                    fontSize = 26.sp,
                    color = RepairLinkColors.Ink
                )
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .background(
                        RepairLinkColors.TealSoft,
                        RoundedCornerShape(25.dp)
                    )
                    .padding(horizontal = 16.dp, vertical = 13.dp)
            ) {
                BasicTextField(
                    value = mensajeEscrito,
                    onValueChange = { mensajeEscrito = it },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    decorationBox = { innerTextField ->
                        if (mensajeEscrito.isEmpty()) {
                            Text(
                                text = "Escribe un mensaje...",
                                color = RepairLinkColors.Muted,
                                fontSize = 14.sp
                            )
                        }
                        innerTextField()
                    }
                )
            }

            Box(
                modifier = Modifier
                    .size(42.dp)
                    .background(
                        RepairLinkColors.PetrolBlue,
                        CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "➤",
                    fontSize = 20.sp,
                    color = RepairLinkColors.White
                )
            }
        }
    }
}

@Composable
private fun MensajeTaller(
    texto: String,
    hora: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .background(
                    RepairLinkColors.White,
                    RoundedCornerShape(14.dp)
                )
                .padding(12.dp)
        ) {
            Text(
                text = texto,
                fontSize = 14.sp,
                color = RepairLinkColors.Ink
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = hora,
                fontSize = 10.sp,
                color = RepairLinkColors.Muted,
                modifier = Modifier.align(Alignment.End)
            )
        }
    }
}

@Composable
private fun MensajeCliente(
    texto: String,
    hora: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .background(
                    RepairLinkColors.Secondary,
                    RoundedCornerShape(14.dp)
                )
                .padding(12.dp)
        ) {
            Text(
                text = texto,
                fontSize = 14.sp,
                color = RepairLinkColors.White
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = hora,
                fontSize = 10.sp,
                color = RepairLinkColors.White,
                modifier = Modifier.align(Alignment.End)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChatTallerScreenPreview() {
    RepairLinkAndroidTheme {
        ChatTallerScreen()
    }
}
