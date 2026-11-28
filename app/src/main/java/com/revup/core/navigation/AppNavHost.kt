package com.revup.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.revup.features.auth.presentation.LoginScreen
import com.revup.features.citasolicitada.presentation.CitaSolicitadaScreen
import com.revup.features.chattaller.presentation.ChatTallerScreen
import com.revup.features.crearcuenta.presentation.CrearCuentaScreen
import com.revup.features.elegirdia.presentation.ElegirDiaScreen
import com.revup.features.elegirhora.presentation.ElegirHoraScreen
import com.revup.features.fichataller.presentation.FichaTallerScreen
import com.revup.features.home.presentation.HomeScreen
import com.revup.features.iniciosesion.presentation.InicioSesionScreen
import com.revup.features.miscoches.presentation.MisCochesScreen
import com.revup.features.miscitas.presentation.MisCitasScreen
import com.revup.features.perfil.presentation.PerfilScreen
import com.revup.features.reparaciones.presentation.ReparacionesScreen
import com.revup.features.seguimiento.presentation.SeguimientoScreen
import com.revup.features.talleres.presentation.TalleresScreen

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
                    navController.navigate(Routes.InicioSesion.route)
                }
            )
        }

        composable(Routes.InicioSesion.route) {
            InicioSesionScreen(
                onVolver = {
                    navController.popBackStack()
                },
                toRegister = {
                    navController.navigate(Routes.CrearCuenta.route)
                },
                toHome = {
                    navController.navigate(Routes.Talleres.route) {
                        popUpTo(Routes.Login.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(Routes.CrearCuenta.route) {
            CrearCuentaScreen(
                onVolver = {
                    navController.popBackStack()
                },
                onIniciarSesion = {
                    navController.navigate(Routes.InicioSesion.route) {
                        popUpTo(Routes.CrearCuenta.route) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
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
                    navController.navigate(Routes.Perfil.route)
                }
            )
        }

        composable(Routes.Reparaciones.route) {
            ReparacionesScreen(
                onTalleres = {
                    navController.navigate(Routes.Talleres.route)
                },
                onMisCoches = {
                    navController.navigate(Routes.MisCoches.route)
                },
                onPerfil = {
                    navController.navigate(Routes.Perfil.route)
                }
            )
        }

        composable(Routes.Talleres.route) {
            TalleresScreen(
                onVerTaller = {
                    navController.navigate(Routes.FichaTaller.route)
                },
                onTalleres = {},
                onMisCoches = {
                    navController.navigate(Routes.MisCoches.route)
                },
                onPerfil = {
                    navController.navigate(Routes.Perfil.route)
                }
            )
        }

        composable(Routes.MisCoches.route) {
            MisCochesScreen(
                onAnadirCoche = {},
                onVerCoche = {
                    navController.navigate(Routes.Seguimiento.route)
                },
                onTalleres = {
                    navController.navigate(Routes.Talleres.route)
                },
                onPerfil = {
                    navController.navigate(Routes.Perfil.route)
                }
            )
        }

        composable(Routes.Perfil.route) {
            PerfilScreen(
                onTalleres = {
                    navController.navigate(Routes.Talleres.route)
                },
                onMisCoches = {
                    navController.navigate(Routes.MisCoches.route)
                },
                onPerfil = {},
                onMisCitas = {
                    navController.navigate(Routes.MisCitas.route)
                }
            )
        }

        composable(Routes.MisCitas.route) {
            MisCitasScreen(
                onVolver = {
                    navController.popBackStack()
                },
                onTalleres = {
                    navController.navigate(Routes.Talleres.route)
                },
                onMisCoches = {
                    navController.navigate(Routes.MisCoches.route)
                },
                onPerfil = {
                    navController.navigate(Routes.Perfil.route)
                }
            )
        }

        composable(Routes.Chat.route) {
            ChatTallerScreen(
                onVolver = {
                    navController.popBackStack()
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
                onVerMisCitas = {
                    navController.navigate(Routes.MisCitas.route)
                },
                onVolverInicio = {
                    navController.navigate(Routes.Talleres.route) {
                        popUpTo(Routes.Talleres.route) {
                            inclusive = false
                        }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(Routes.Seguimiento.route) {
            SeguimientoScreen(
                onVolver = {
                    navController.popBackStack()
                }
            )
        }
    }
}
