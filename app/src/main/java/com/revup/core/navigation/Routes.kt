package com.revup.core.navigation
import kotlinx.serialization.Serializable

@Serializable
sealed class Routes (val route: String) {

    @Serializable
    object Login : Routes("login")

    @Serializable
    object Home : Routes("home")
    @Serializable
    object Reparaciones : Routes("reparaciones")

    @Serializable
    object MisCoches : Routes("mis_coches")

    @Serializable
    object FichaTaller : Routes("ficha_taller")
}