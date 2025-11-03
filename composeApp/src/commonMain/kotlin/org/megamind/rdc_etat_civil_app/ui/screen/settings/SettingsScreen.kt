package org.megamind.rdc_etat_civil_app.ui.screen.settings


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.window.core.layout.WindowSizeClass


@Composable
fun SettingsScreen(modifier: Modifier = Modifier, windowSizeClass: WindowSizeClass) {
    SettingsScreenContent()


}

@Composable
private fun SettingsScreenContent() {
    Scaffold { innerPadding ->


        Box(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {


            Text("Settings")

        }
    }
}