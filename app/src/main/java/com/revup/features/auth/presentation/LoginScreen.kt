package com.revup.features.auth.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.revup.R
import com.revup.core.ui.theme.RepairLinkAndroidTheme
import com.revup.core.ui.theme.RepairLinkColors


@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
fun LoginScreenPreview(){
    RepairLinkAndroidTheme(){
        LoginScreen(onAcceder = {})
    }
}
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onAcceder: () -> Unit
){
    val viewModel = remember { LoginViewModel() }

    Scaffold(
        modifier = modifier.fillMaxSize()
    ) {innerPadding -> Column(
        modifier = Modifier.consumeWindowInsets(innerPadding)
            .padding(innerPadding)
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Spacer(modifier = Modifier.weight(1f))
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = RepairLinkColors.OrangePrimary
                )
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_wrench),
                    contentDescription = null,
                    tint = RepairLinkColors.White,
                    modifier = Modifier.padding(20.dp).size(40.dp)
                )
            }

            Row {
                Text(text = "Repair", fontSize = 34.sp, fontWeight = FontWeight.Bold, color = RepairLinkColors.White)
                Text(text = "Link", fontSize = 34.sp, fontWeight = FontWeight.Bold, color = RepairLinkColors.OrangePrimary)
            }

            Text(
                text = "Tu taller y tu siempre conectados",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                color = RepairLinkColors.LightGrayText
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Card(
                    shape = CircleShape,
                    colors = CardDefaults.cardColors(
                        containerColor = RepairLinkColors.Secondary
                    )
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_car),
                        contentDescription = null,
                        tint = RepairLinkColors.White,
                        modifier = Modifier.padding(14.dp).size(24.dp)
                    )
                }
                Text(text = "--", fontSize = 20.sp, color = RepairLinkColors.LightGrayText)
                Card(
                    shape = CircleShape,
                    colors = CardDefaults.cardColors(
                        containerColor = RepairLinkColors.Secondary
                    )
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_wrench),
                        contentDescription = null,
                        tint = RepairLinkColors.White,
                        modifier = Modifier.padding(14.dp).size(24.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.weight(2f))

        Button(
            onClick = {onAcceder()},
            modifier = Modifier.fillMaxWidth()
        ) { Text("Iniciar sesión", fontSize = 16.sp, fontWeight = FontWeight.Bold)}
    }

    }

}
