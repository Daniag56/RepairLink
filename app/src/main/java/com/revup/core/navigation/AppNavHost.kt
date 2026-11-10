package com.revup.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavHostController
import androidx.compose.ui.Modifier
import com.revup.features.auth.presentation.LoginScreen
import com.revup.features.citasolicitada.presentation.CitaSolicitadaScreen
import com.revup.features.elegirdia.presentation.ElegirDiaScreen
import com.revup.features.elegirhora.presentation.ElegirHoraScreen
import com.revup.features.fichataller.presentation.FichaTallerScreen
import com.revup.features.home.presentation.HomeScreen
import com.revup.features.miscoches.presentation.MisCochesScreen
import com.revup.features.panel.presentation.PanelScreen
import com.revup.features.talleres.presentation.TalleresScreen
import com.revup.features.reparaciones.presentation.ReparacionesScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Routes.Login.route,
        modifier = modifier
    ) {
        composable(Routes.Login.route) {
            LoginScreen(
                onAcceder = {
                    navController.navigate(Routes.MisCoches.route)
                }
            )
        }

        composable(Routes.Home.route) {
            HomeScreen(
                onTalleres = {
                    navController.navigate(Routes.Talleres.route)
                },
                onMisCoches = {
                    navController.navigate(Routes.Reparaciones.route)
                },
                onPerfil = {
                }
            )
        }
        composable(Routes.Reparaciones.route) {
            ReparacionesScreen(
                onTalleres = {
                    navController.navigate(Routes.Talleres.route)
                },
                onMisCoches = {
                },
                onPerfil = {
                }
            )
        }
        composable(Routes.Talleres.route) {
            TalleresScreen(
                onTalleres = {
                },
                onMisCoches = {
                    navController.navigate(Routes.MisCoches.route)
                },
                onPerfil = {
                }
            )
        }

        composable(Routes.MisCoches.route) {
            MisCochesScreen(
                onAnadirCoche = { },
                onVerTaller = {
                    navController.navigate(Routes.FichaTaller.route)
                },
                onTalleres = {
                    navController.navigate(Routes.Talleres.route)
                },
                onPerfil = {
                }
            )
        }

        composable(Routes.FichaTaller.route) {
            FichaTallerScreen(
                onVolver = {
                    navController.popBackStack()
                },
                onReservarCita = {
                    navController.navigate(Routes.ElegirDia.route)
                }
            )
        }

        composable(Routes.ElegirDia.route) {
            ElegirDiaScreen(
                onVolver = {
                    navController.popBackStack()
                },
                onVerHoras = {
                    navController.navigate(Routes.ElegirHora.route)
                }
            )
        }

        composable(Routes.ElegirHora.route) {
            ElegirHoraScreen(
                onVolver = {
                    navController.popBackStack()
                },
                onSolicitarCita = {
                    navController.navigate(Routes.CitaSolicitada.route)
                }
            )
        }

        composable(Routes.CitaSolicitada.route) {
            CitaSolicitadaScreen(
                onVerMisCitas = { },
                onVolverInicio = {
                    navController.navigate(Routes.MisCoches.route)
                }
            )
        }
    }
}
