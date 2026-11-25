package com.revup.features.iniciosesion.presentation

import android.os.Message
import androidx.compose.foundation.text.input.TextFieldState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revup.features.auth.data.User
import com.revup.features.auth.data.remote.exception.CredentialsNotValidException
import com.revup.features.auth.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class InicioSesionViewModel @Inject constructor(
    private val repository: AuthRepository,
): ViewModel(){
    private val _uiState = MutableStateFlow<InicioSesionUiState>(InicioSesionUiState.Initial)
    val uiState = _uiState.asStateFlow()

    val emailTextFieldState = TextFieldState()
    val passwordTextFieldState = TextFieldState()

    init {
        viewModelScope.launch {
            repository.user.collect { result ->
                if (result.isSuccess) {
                    _uiState.value = InicioSesionUiState.LoggedIn(user = result.getOrNull()!!)
                }
                else{
                    if (result.exceptionOrNull()!! is CredentialsNotValidException)
                        _uiState.value = InicioSesionUiState.Error("Correo o contraseña incorrectos")
                    else
                        _uiState.value = InicioSesionUiState.Initial
                }
                }
            }
        }
    fun login(){
        viewModelScope.launch {
            _uiState.value = InicioSesionUiState.LoggingIn
            val email = emailTextFieldState.text.toString()
            val password = passwordTextFieldState.text.toString()
            repository.login(email,password)
        }
    }
    }


sealed class InicioSesionUiState{
    object Initial: InicioSesionUiState()
    object LoggingIn: InicioSesionUiState()
    data class Error(val message: String): InicioSesionUiState()
    data class LoggedIn(val user: User): InicioSesionUiState()
}