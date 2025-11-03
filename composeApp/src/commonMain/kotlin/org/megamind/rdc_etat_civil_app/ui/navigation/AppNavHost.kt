package org.megamind.rdc_etat_civil_app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.window.core.layout.WindowSizeClass
import org.megamind.rdc_etat_civil_app.ui.screen.auth.LoginScreen
import org.megamind.rdc_etat_civil_app.ui.screen.dashBoard.DashBoardScreen
import org.megamind.rdc_etat_civil_app.ui.screen.deces.DecesScreen
import org.megamind.rdc_etat_civil_app.ui.screen.mariage.MariageScreen
import org.megamind.rdc_etat_civil_app.ui.screen.naissance.NaissanceScreen
import org.megamind.rdc_etat_civil_app.ui.screen.settings.SettingsScreen


@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = Destination.LOGIN.name,
    windowSizeClass: WindowSizeClass
) {


    NavHost(navController = navController, startDestination = startDestination) {

        composable(route = Destination.LOGIN.name) {

            LoginScreen(
                windowSizeClass = windowSizeClass,
                onNavigateToMain = {

                    navController.navigate(Destination.DASHBOARD.name) {
                        popUpTo(Destination.LOGIN.name) {
                            inclusive = true
                        }
                    }


                }
            )

        }


        composable(route = Destination.NAISSANCE.name) {
            NaissanceScreen(windowSizeClass = windowSizeClass)
        }

        composable(route = Destination.MARIAGE.name) {

            MariageScreen(windowSizeClass = windowSizeClass)
        }

        composable(route = Destination.DECES.name) {

            DecesScreen(windowSizeClass = windowSizeClass)
        }

        composable(route = Destination.DASHBOARD.name) {

            DashBoardScreen(windowSizeClass = windowSizeClass)
        }

        composable(route = Destination.SETTINGS.name) {
            SettingsScreen(windowSizeClass = windowSizeClass)
        }


    }
}