package com.revup.features.iniciosesion.presentation

import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.revup.core.ui.theme.RepairLinkAndroidTheme


@Preview
@Composable
fun InicioSesionScreenPreview(){
    RepairLinkAndroidTheme() {
        InicioSesionScreen()
    }
}

@Composable

fun InicioSesionScreen(
    modifier: Modifier = Modifier
){
    val viewModel = remember { InicioSesionViewModel() }

    Scaffold(
        modifier = modifier.fillMaxSize()
    ) {innerPadding -> Column(
        modifier = Modifier.consumeWindowInsets(innerPadding)
            .padding(innerPadding)
            .fillMaxSize()
            .padding(20.dp)
    )  {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){
            Button(onClick = {}){Text("<", fontSize = 18.sp, fontWeight = FontWeight.Bold)}
            Card{
                Text(text = "", modifier = Modifier.padding(30.dp))
            }
            //Cabecera inicio Sesion
            Column{
                Text(text = "Hola de nuevo", fontSize = 26.sp, fontWeight = FontWeight.Bold)
                Text(text = "Inicia sesion para ver tus coches y tus citas", fontSize = 14.sp)
            }
            //Seccion de correo
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(text = "Correo electronico", fontSize = 13.sp, fontWeight = FontWeight.Bold)
                Card(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Correo@correo.com",
                        fontSize = 16.sp,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }

            //Contraseña
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(text = "Contraseña", fontSize =  13.sp, fontWeight = FontWeight.Bold)
                Card(modifier = Modifier.fillMaxWidth()){
                    Text(
                        text = "*******",
                        fontSize = 16.sp,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.weight(1f))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                onClick = { },
                modifier = Modifier.fillMaxWidth()
            ) {Text("Iniciar Sesion", fontSize = 16.sp, fontWeight = FontWeight.Bold)}

            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)){
                Text(text = "¿No tienes cuenta?", fontSize = 14.sp)
                Text(text = "Registrate", fontSize = 14.sp, fontWeight = FontWeight.Bold)
            }
        }
    }}
}