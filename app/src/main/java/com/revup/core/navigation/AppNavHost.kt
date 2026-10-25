package com.revup.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavHostController
import androidx.compose.ui.Modifier
import com.revup.features.auth.presentation.LoginScreen
import com.revup.features.home.presentation.HomeScreen
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
                    navController.navigate(Routes.Home.route)
                }
            )
        }

        composable(Routes.Home.route) {
            HomeScreen(
                onReparaciones = {
                    navController.navigate(Routes.Reparaciones.route)
                }
            )
        }
        composable(Routes.Reparaciones.route){
            ReparacionesScreen(
                onHome = {
                    navController.navigate(Routes.Home.route)
                }
            )
        }
    }
}
