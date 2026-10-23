package com.revup.features.auth.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.revup.core.ui.theme.RepairLinkAndroidTheme


@Preview(showBackground = true)
@Composable
fun LoginScreenPreview(){
    RepairLinkAndroidTheme(){
        LoginScreen()
    }
}
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
){
    val viewModel = remember { LoginViewModel() }

    Scaffold(
        modifier = modifier.fillMaxSize()
    ) {innerPadding -> Column(
        modifier = Modifier.consumeWindowInsets(innerPadding)
            .padding(innerPadding)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween

    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "RepairLink", fontSize = 40.sp)
            }
            Text(
                text = "Tu taller y tu siempre conectados",
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
        }
        Column(modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {

            Button(
                onClick = {viewModel.onAcceder()},
                modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp)
            ) {Text("Acceder", fontSize = 16.sp)}
        }
        Text(
            text = "¿Ya tienes cuenta? Inicia sesion",
            fontSize = 14.sp,
            textAlign = TextAlign.Center,
        )
    }

    }

}