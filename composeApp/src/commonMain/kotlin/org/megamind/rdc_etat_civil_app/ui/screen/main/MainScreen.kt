package org.megamind.rdc_etat_civil_app.ui.screen.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.BabyChangingStation
import androidx.compose.material.icons.rounded.Dashboard
import androidx.compose.material.icons.rounded.Grade
import androidx.compose.material.icons.rounded.People
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import com.auth0.jwt.JWT
import org.koin.compose.viewmodel.koinViewModel
import org.megamind.rdc_etat_civil_app.di.TokenManager
import org.megamind.rdc_etat_civil_app.ui.NavBarItem
import org.megamind.rdc_etat_civil_app.ui.component.DrawerHeader
import org.megamind.rdc_etat_civil_app.ui.component.NavigationDrawerItems
import org.megamind.rdc_etat_civil_app.ui.navigation.AppNavHost
import org.megamind.rdc_etat_civil_app.ui.navigation.Destination

@Composable
fun MainScreen(
    windowSizeClass: WindowSizeClass
) {


    MainScreenContent(windowSizeClass = windowSizeClass)

}

@Composable
fun MainScreenContent(windowSizeClass: WindowSizeClass) {


    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination


    // Déterminer si on doit afficher le BottomBar
    val showBottomBar = currentDestination?.hierarchy?.any {
        it.route in listOf(
            Destination.NAISSANCE.name,
            Destination.MARIAGE.name,
            Destination.DECES.name,
            Destination.DASHBOARD.name,
            Destination.SETTINGS.name

        )
    } == true

    val isCompact =
        windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.COMPACT || windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.MEDIUM


    val navBarItems = listOf(
        NavBarItem("Tableau de bord", Icons.Rounded.Dashboard, Destination.DASHBOARD.name),
        NavBarItem("Naissances", Icons.Rounded.BabyChangingStation, Destination.NAISSANCE.name),
        NavBarItem("Mariages", Icons.Rounded.People, Destination.MARIAGE.name),
        NavBarItem("Decès", Icons.Rounded.Grade, Destination.DECES.name),
        NavBarItem("Paramètres", Icons.Rounded.Settings, Destination.SETTINGS.name),

        )

    if (isCompact) {

        AdminCompactMainScreen(
            windowSizeClass = windowSizeClass,
            navBarItems = navBarItems,
            showBottomBar = showBottomBar,
            currentDestination = currentDestination,
            navController = navController,


            )
    } else {
        AdminExpendedMainScreen(
            windowSizeClass = windowSizeClass,
            navBarItems = navBarItems,
            showBottomBar = showBottomBar,
            currentDestination = currentDestination,
            navController = navController

        )
    }

}


@Composable
private fun AdminCompactMainScreen(
    windowSizeClass: WindowSizeClass,
    navBarItems: List<NavBarItem>,
    showBottomBar: Boolean,
    currentDestination: NavDestination?,
    navController: NavHostController,
) {

    Scaffold(
        bottomBar = {

            AnimatedVisibility(
                visible = showBottomBar,
                enter = fadeIn(),

                ) {
                NavigationBar(modifier = Modifier, content = {

                    navBarItems.forEachIndexed { index, item ->

                        val selected = currentDestination?.hierarchy?.any {
                            it.route == item.route
                        }
                        NavigationBarItem(
                            selected = selected == true,
                            onClick = {
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = null
                                )
                            },
                            label = {
                                Text(text = item.label)
                            },
                        )
                    }
                })
            }

        }) { innerPadding ->


        AppNavHost(
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            windowSizeClass = windowSizeClass
        )


    }


}


@Composable
private fun AdminExpendedMainScreen(
    windowSizeClass: WindowSizeClass,
    navBarItems: List<NavBarItem>,
    showBottomBar: Boolean,
    currentDestination: NavDestination?,
    navController: NavHostController,
) {


    val userName = JWT.decode(TokenManager.token).getClaim("sub").asString()
    val userRole = JWT.decode(TokenManager.token).getClaim("role").asString()



    Scaffold() { innerPadding ->


        PermanentNavigationDrawer(

            drawerContent = {

                AnimatedVisibility(
                    visible = showBottomBar,
                    enter = fadeIn(),
                )
                {
                    PermanentDrawerSheet(
                        modifier = Modifier.width(240.dp),
                        drawerContainerColor = MaterialTheme.colorScheme.primary.copy(alpha = .07f)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxHeight() // Important pour que le Column prenne toute la hauteur
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            DrawerHeader(userName = userName, userRole = userRole)

                            // Les premiers items de navigation (sans les 2 derniers)
                            navBarItems.dropLast(2).forEachIndexed { index, item ->
                                val selected = currentDestination?.hierarchy?.any {
                                    it.route == item.route
                                }
                                NavigationDrawerItems(
                                    modifier = Modifier,
                                    selected = selected == true,
                                    index = index,
                                    label = item.label,
                                    icon = item.icon,
                                    onItemClick = {
                                        navController.navigate(item.route) {
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    }
                                )
                            }

                            // Spacer qui pousse les 2 derniers items vers le bas
                            Spacer(modifier = Modifier.weight(1f))

                            // Optionnel : Divider avant les items du bas
                            Divider(
                                modifier = Modifier.padding(vertical = 8.dp),
                                color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)
                            )

                            // Les 2 derniers items (Paramètres et Déconnexion) en bas
                            navBarItems.takeLast(2).forEachIndexed { relativeIndex, item ->
                                val index =
                                    navBarItems.size - 2 + relativeIndex // Index réel dans la liste complète
                                val selected = currentDestination?.hierarchy?.any {
                                    it.route == item.route
                                }
                                val isLogout =
                                    index == navBarItems.lastIndex // Vérifie si c'est l'item de déconnexion


                                NavigationDrawerItems(
                                    modifier = Modifier,
                                    selected = selected == true,
                                    index = index,
                                    label = item.label,
                                    icon = item.icon,
                                    onItemClick = {
                                        navController.navigate(item.route) {
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }

                                    }
                                )
                            }
                        }
                    }
                }
            }
        ) {

            AppNavHost(
                modifier = Modifier.padding(innerPadding),
                navController = navController,
                windowSizeClass = windowSizeClass
            )


        }
    }
}