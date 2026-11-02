package com.revup.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavHostController
import androidx.compose.ui.Modifier
import com.revup.features.aceptarcoche.presentation.AceptarCocheScreen
import com.revup.features.auth.presentation.LoginScreen
import com.revup.features.fichataller.presentation.FichaTallerScreen
import com.revup.features.home.presentation.HomeScreen
import com.revup.features.miscoches.presentation.MisCochesScreen
import com.revup.features.panel.presentation.PanelScreen
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
            PanelScreen(
                onAceptarCoche = {
                    navController.navigate(Routes.Reparaciones.route)
                }
            )
        }
        composable(Routes.Reparaciones.route){
            AceptarCocheScreen(
                onVolver = {
                    navController.popBackStack()
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
