package com.revup.features.perfil.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.revup.R
import com.revup.core.ui.components.BarraInferior
import com.revup.core.ui.theme.RepairLinkAndroidTheme
import com.revup.core.ui.theme.RepairLinkColors

@Composable
fun PerfilScreen(
    onTalleres: () -> Unit,
    onMisCoches: () -> Unit,
    onPerfil: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: PerfilViewModel = hiltViewModel()
) {
    PerfilContent(
        nombre = viewModel.nombre,
        correo = viewModel.correo,
        iniciales = viewModel.iniciales,
        notificaciones = viewModel.notificaciones,
        onTalleres = onTalleres,
        onMisCoches = onMisCoches,
        onPerfil = onPerfil,
        modifier = modifier
    )
}

@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
fun PerfilScreenPreview() {
    RepairLinkAndroidTheme {
        PerfilContent(
            nombre = "Laura Gómez",
            correo = "laura.gomez@email.com",
            iniciales = "LG",
            notificaciones = 3,
            onTalleres = {},
            onMisCoches = {},
            onPerfil = {}
        )
    }
}

@Composable
fun PerfilContent(
    nombre: String,
    correo: String,
    iniciales: String,
    notificaciones: Int,
    onTalleres: () -> Unit,
    onMisCoches: () -> Unit,
    onPerfil: () -> Unit,
    modifier: Modifier = Modifier
) {
    var avisosActivos by remember { mutableStateOf(true) }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            BarraInferior(
                seleccionada = "Perfil",
                onTalleres = onTalleres,
                onMisCoches = onMisCoches,
                onPerfil = onPerfil
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Card(
                    modifier = Modifier.size(64.dp),
                    shape = CircleShape
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = iniciales,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = nombre,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = correo,
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column {
                    FilaPerfil("◷", "Mis citas")
                    Divider()
                    FilaPerfil(
                        titulo = "Mis coches",
                        iconoResource = R.drawable.ic_car_tab
                    )
                    Divider()
                    FilaPerfil("●", "Notificaciones", notificaciones)
                    Divider()
                    FilaPerfil("◇", "Privacidad y seguridad")
                    Divider()
                    FilaPerfil("?", "Ayuda")
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
                        text = "Avisos en el móvil",
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.weight(1f)
                    )

                    Switch(
                        checked = avisosActivos,
                        onCheckedChange = { avisosActivos = it },
                        colors = SwitchDefaults.colors(
                            checkedTrackColor = RepairLinkColors.Success,
                            checkedThumbColor = RepairLinkColors.White
                        )
                    )
                }
            }

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "↪",
                        fontSize = 20.sp,
                        color = MaterialTheme.colorScheme.error
                    )

                    Text(
                        text = "Cerrar sesión",
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun FilaPerfil(
    icono: String = "",
    titulo: String,
    notificaciones: Int = 0,
    iconoResource: Int = 0
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        if (iconoResource != 0) {
            Icon(
                painter = painterResource(iconoResource),
                contentDescription = null,
                modifier = Modifier.size(20.dp),
                tint = MaterialTheme.colorScheme.primary
            )
        } else {
            Text(
                text = icono,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.primary
            )
        }

        Text(
            text = titulo,
            modifier = Modifier.weight(1f),
            fontSize = 15.sp
        )

        if (notificaciones > 0) {
            Card(
                modifier = Modifier.size(24.dp),
                shape = CircleShape,
                colors = CardDefaults.cardColors(
                    containerColor = RepairLinkColors.OrangePrimary
                )
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = notificaciones.toString(),
                        color = RepairLinkColors.Black,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Text(
            text = "›",
            fontSize = 24.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
