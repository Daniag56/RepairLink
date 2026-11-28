package com.revup.features.crearcuenta.presentation

import androidx.compose.foundation.text.input.TextFieldState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.revup.features.auth.data.User
import com.revup.features.auth.data.repository.AuthRepository
import com.revup.features.auth.data.remote.exception.CredentialsNotValidException
import com.revup.features.auth.data.remote.exception.EmailAlreadyRegisteredException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CrearCuentaViewModel @Inject constructor(
    private val repository: AuthRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<CrearCuentaUiState>(CrearCuentaUiState.Initial)

    val uiState = _uiState.asStateFlow()

    val nombreTextFieldState = TextFieldState()
    val correoTextFieldState = TextFieldState()
    val telefonoTextFieldState = TextFieldState()
    val contrasenaTextFieldState = TextFieldState()

    private val _aceptarCondiciones = MutableStateFlow(false)
    val aceptarCondiciones = _aceptarCondiciones.asStateFlow()

    fun onAceptarCondiciones(aceptadas: Boolean) {
        _aceptarCondiciones.value = aceptadas
    }

    fun register() {
        val nombre = nombreTextFieldState.text.toString()
        val correo = correoTextFieldState.text.toString()
        val contrasena = contrasenaTextFieldState.text.toString()

        viewModelScope.launch {
            _uiState.value = CrearCuentaUiState.Registering

            try {
                repository.register(
                    email = correo,
                    name = nombre,
                    password = contrasena
                )

                val result = repository.user.first()

                if (result.isSuccess) {
                    _uiState.value =
                        CrearCuentaUiState.Registered(
                            user = result.getOrNull()!!
                        )
                } else {
                    val exception = result.exceptionOrNull()

                    when (exception) {
                        is EmailAlreadyRegisteredException -> {
                            _uiState.value =
                                CrearCuentaUiState.Error(
                                    "Este correo ya está registrado"
                                )
                        }

                        is CredentialsNotValidException -> {
                            _uiState.value =
                                CrearCuentaUiState.Error(
                                    "No se ha podido crear la cuenta"
                                )
                        }

                        else -> {
                            _uiState.value =
                                CrearCuentaUiState.Error(
                                    "Ha ocurrido un error al registrarte"
                                )
                        }
                    }
                }
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                _uiState.value =
                    CrearCuentaUiState.Error(
                        "No se ha podido conectar con el servidor"
                    )
            }
        }
    }
}

sealed class CrearCuentaUiState {
    object Initial : CrearCuentaUiState()

    object Registering : CrearCuentaUiState()

    data class Error(val message: String) : CrearCuentaUiState()

    data class Registered(val user: User) : CrearCuentaUiState()
}
