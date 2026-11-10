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

    @Serializable
    object ElegirDia : Routes("elegir_dia")

    @Serializable
    object ElegirHora : Routes("elegir_hora")

    @Serializable
    object CitaSolicitada : Routes("cita_solicitada")
    @Serializable
    object Talleres : Routes("talleres")


    @Serializable
    object Perfil : Routes("perfil")
}
