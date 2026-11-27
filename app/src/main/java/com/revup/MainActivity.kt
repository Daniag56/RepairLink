package com.revup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.revup.core.navigation.AppNavHost
import com.revup.core.navigation.Routes
import com.revup.core.ui.theme.RepairLinkAndroidTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        splashScreen.setKeepOnScreenCondition { viewModel.uiState.value == MainUiState.Cargando }
        setContent {
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            RepairLinkAndroidTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val modifier = Modifier
                        .consumeWindowInsets(innerPadding)
                        .padding(innerPadding).fillMaxSize()
                    when (uiState) {
                        MainUiState.Cargando -> { }
                        MainUiState.SinSesion -> AppNavHost(
                            startDestination = Routes.Login.route,
                            modifier = modifier
                        )
                        MainUiState.ConSesion -> AppNavHost(
                            startDestination = Routes.Talleres.route,
                            modifier = modifier
                        )
                    }
                }
            }
        }
    }
}
