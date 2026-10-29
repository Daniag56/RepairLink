package com.revup.features.seguimiento.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
    )  { }}
}