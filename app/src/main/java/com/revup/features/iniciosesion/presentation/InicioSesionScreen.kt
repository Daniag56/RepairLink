package com.revup.features.iniciosesion.presentation


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedSecureTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.revup.core.ui.theme.RepairLinkAndroidTheme



@Composable
fun InicioSesionScreen(
    modifier: Modifier = Modifier,
    viewModel: InicioSesionViewModel = hiltViewModel(),
    onVolver: () -> Unit,
    toRegister: () -> Unit,
    toHome: () -> Unit,
){
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when(uiState){
        InicioSesionUiState.Initial ->{
        InicioSesionContent(
            modifier = modifier,
            emailState = viewModel.emailTextFieldState,
            passwordState = viewModel.passwordTextFieldState,
            isLogginIn = false,
            errorMessage = null,
            onLoginClick = { viewModel.login() },
            onRegisterClick = toRegister,
            onVolver = onVolver
        )
        }
        is InicioSesionUiState.Error -> {
            InicioSesionContent(
                modifier = modifier,
                emailState = viewModel.emailTextFieldState,
                passwordState = viewModel.passwordTextFieldState,
                isLogginIn = false,
                errorMessage = (uiState as InicioSesionUiState.Error).message,
                onLoginClick = { viewModel.login() },
                onRegisterClick = toRegister,
                onVolver = onVolver
            )
        }
        is InicioSesionUiState.LoggedIn -> {
            LaunchedEffect(key1 = Unit) {
                toHome()
            }
        }

        InicioSesionUiState.LoggingIn -> {
            InicioSesionContent(
                modifier = modifier,
                emailState = viewModel.emailTextFieldState,
                passwordState = viewModel.passwordTextFieldState,
                isLogginIn = true,
                errorMessage = null,
                onLoginClick = { viewModel.login() },
                onRegisterClick = toRegister,
                onVolver = onVolver
            )
            }
        }
    }

@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
fun InicioSesionScreenPreview(){
    RepairLinkAndroidTheme {
        InicioSesionContent(
            emailState = TextFieldState(),
            passwordState = TextFieldState(),
            isLogginIn = false,
            errorMessage = null,
            onLoginClick = {},
            onRegisterClick = {},
            onVolver = {}
        )
    }
}

@Composable

fun InicioSesionContent(
    modifier: Modifier = Modifier,
    emailState: TextFieldState,
    passwordState: TextFieldState,
    isLogginIn: Boolean,
    errorMessage: String?,
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit,
    onVolver: () -> Unit,

){
    val isError = errorMessage != null

    Scaffold(
        modifier = modifier.fillMaxSize()
    ) {innerPadding -> Column (
        modifier = Modifier.consumeWindowInsets(innerPadding)
            .padding(innerPadding)
            .fillMaxSize()
            .padding(20.dp)
    ){
        Button(onClick = onVolver) {
            Text("<", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Card {
            Text(text = "",modifier = Modifier.padding(30.dp))
        }
        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Hola de nuevo", fontSize = 26.sp, fontWeight = FontWeight.Bold)
        Text(text = "Inicia Sesion para ver tus coches y citas", fontSize = 14.sp)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            label = {Text("Correo electronico")},
            state = emailState,
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLogginIn,
            lineLimits = TextFieldLineLimits.SingleLine,
            isError = isError
        )

        errorMessage?.let {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onLoginClick,
            modifier = Modifier.fillMaxWidth(),
            enabled = !isLogginIn && passwordState.text.isNotBlank() && emailState.text.isNotBlank()
        ) {
            if (isLogginIn){
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = MaterialTheme.colorScheme.onPrimary,
                    strokeWidth = 2.dp
                )
            }else{
                Text("Iniciar Sesion", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        TextButton(
            onClick = onRegisterClick,
            enabled = !isLogginIn
        ){
            Text("¿No tienes cuenta? Registrate")
        }
    } }
}