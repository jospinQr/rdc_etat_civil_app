package org.megamind.rdc_etat_civil_app.navigation

import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.internal.composableLambda
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController
import androidx.window.core.layout.WindowSizeClass
import org.megamind.rdc_etat_civil_app.ui.screen.auth.LoginScreen


@Composable
fun AppNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = Destination.LOGIN.name
) {
    val windowSizeClass: WindowSizeClass = currentWindowAdaptiveInfo().windowSizeClass

    NavHost(navController = navController, startDestination = startDestination) {

        composable(route = Destination.LOGIN.name) {

            LoginScreen(
                windowSizeClass=windowSizeClass,
                onNavigateToMain = {}
            )

        }
    }
}