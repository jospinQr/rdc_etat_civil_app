package org.megamind.rdc_etat_civil_app

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.megamind.rdc_etat_civil_app.di.initKoin

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Rdc_etat_civil_app",
    ) {
        initKoin()
        App()
    }
}