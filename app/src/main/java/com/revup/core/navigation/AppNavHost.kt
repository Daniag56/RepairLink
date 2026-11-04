package com.revup.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavHostController
import androidx.compose.ui.Modifier
import com.revup.features.auth.presentation.LoginScreen
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
                    navController.navigate(Routes.Reparaciones.route)
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
                }
            )
        }

        composable(Routes.FichaTaller.route) {
            FichaTallerScreen(
                onVolver = {
                    navController.popBackStack()
                },
                onVerHoras = { }
            )
        }
    }
}
