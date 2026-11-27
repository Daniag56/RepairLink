package com.revup.core.ui.components

import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.revup.R
import com.revup.core.ui.theme.RepairLinkAndroidTheme

@Composable
fun BotonVolver(
    onVolver: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(onClick = { onVolver() }, modifier = modifier) {
        Icon(painter = painterResource(R.drawable.ic_left), contentDescription = null)
    }
}

@Preview(showBackground = true)
@Composable
fun BotonVolverPreview() {
    RepairLinkAndroidTheme {
        BotonVolver(onVolver = {})
    }
}
