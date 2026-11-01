package com.revup.features.seguimiento.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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

@Preview(showBackground = true)
@Composable
fun SeguimientoScreenPreview(){
    RepairLinkAndroidTheme() {
        SeguimientoScreen()
    }
}

@Composable
fun SeguimientoScreen(
    modifier: Modifier = Modifier
){
    val viewModel = remember { SeguimientoViewModel() }

    Scaffold(
        modifier = modifier.fillMaxSize()
    ) {innerPadding -> Column(
        modifier = Modifier.consumeWindowInsets(innerPadding)
            .padding(innerPadding)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    )  {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            //Cabecera: esc,info.coche y estado.coche
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                Button(onClick = { }) { Text("<")}
                Column{
                    Text(text = "COCHE", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Text(text = "KM - TALLER", fontSize = 12.sp)
                }
                Card {
                    Text(text = "ESTADO DE LA REPARACION", fontSize = 12.sp, modifier = Modifier.padding(16.dp))
                }
            }
            //Pasos de la reparacion
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                PasoSeguimiento("✓","TITULO SEG","DETALLE SEG")
                PasoSeguimiento("✓","TITULO SEG","DETALLE SEG")
                PasoSeguimiento("✓","TITULO SEG","DETALLE SEG")
                PasoSeguimiento("✓","TITULO SEG","DETALLE SEG")
            }
        }
        Button(
            onClick = { },
            modifier = Modifier.fillMaxWidth()
        ) { Text("Escribir al taller", fontSize = 16.sp)}
    }}
}

@Composable
fun PasoSeguimiento(
    icono: String,
    titulo: String,
    detalle: String
){
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = icono, fontSize = 16.sp)
        Column{
            Text(text = titulo, fontSize = 16.sp, fontWeight =  FontWeight.Bold)
            Text(text = detalle, fontSize = 14.sp)
        }
    }
}