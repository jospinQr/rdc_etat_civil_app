package org.megamind.rdc_etat_civil_app

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.window.core.layout.WindowSizeClass
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.megamind.rdc_etat_civil_app.ui.screen.main.MainScreen

@Composable
@Preview
fun App() {
    MaterialTheme {

        val windowSizeClass: WindowSizeClass = currentWindowAdaptiveInfo().windowSizeClass
        MainScreen(
            windowSizeClass = windowSizeClass
        )

    }
}