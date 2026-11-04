package com.revup.features.seguimiento.presentation

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
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.SpaceBetween
    )  {
        Column(modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)) {

            //Cabecera: esc,info.coche y estado.coche
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Button(onClick = { }) { Text("<", fontSize = 18.sp, fontWeight = FontWeight.Bold)}
                Spacer(modifier = Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)){
                    Text(text = "Renault Clio 3", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Text(text = "4821KM - TALLER Hermano Ruiz", fontSize = 12.sp)
                }
                Card {
                    Text(text = "En reparacion", fontSize = 12.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp))
                }
            }
            Card(modifier = Modifier.fillMaxWidth()){
                Row(
                    modifier = Modifier.fillMaxWidth().padding(4.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ){
                    Button(onClick = {}, modifier = Modifier.weight(1f)) {
                        Text("Estado", fontWeight = FontWeight.Bold)
                    }
                    Button(onClick = { },modifier = Modifier.weight(1f)) {
                        Text("Chat", fontWeight = FontWeight.Bold)
                    }
                }
            }
            //Pasos de la reparacion
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                PasoSeguimiento("✓", "Coche recibido", "Recibido en el taller · Lun 5 oct · 09:12")
                PasoSeguimiento("✓", "Revisión completada", "Discos y pastillas al límite · Lun 5 oct · 12:40")
                PasoSeguimiento("✓", "Presupuesto aceptado", "186,40 € · aceptado desde la app · 13:05")
                PasoSeguimiento("✓", "Piezas pedidas", "Pastillas y discos delanteros · Mar 6 oct")

                Row(horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically){
                    IconoPaso("🔧")
                    Card(modifier = Modifier.weight(1f)) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(text = "En reparación", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                            Text(text = "Cambio de pastillas y discos · Fin previsto hoy, 18:00", fontSize = 14.sp)
                        }
                    }
                }
                PasoSeguimiento("⚑", "Listo para recoger", "Te avisaremos por aquí")
            }
        }
        Button(
            onClick = { },
            modifier = Modifier.fillMaxWidth()
        ) { Text("Escribir al taller", fontSize = 16.sp)}
    }}
}

@Composable
fun IconoPaso(
    icono: String
){
    Card {
        Text(
            text = icono,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 8.dp,vertical = 4.dp)
        )
    }
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
        IconoPaso(icono)
        Column{
            Text(text = titulo, fontSize = 16.sp, fontWeight =  FontWeight.Bold)
            Text(text = detalle, fontSize = 14.sp)
        }
    }
}