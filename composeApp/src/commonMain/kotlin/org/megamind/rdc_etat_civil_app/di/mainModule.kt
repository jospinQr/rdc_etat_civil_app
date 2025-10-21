package org.megamind.rdc_etat_civil_app.di

import androidx.lifecycle.viewmodel.compose.viewModel
import org.koin.core.annotation.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import org.megamind.rdc_etat_civil_app.ui.screen.auth.AuthViewModel


val mainModule = module {


    viewModel {
        AuthViewModel()
    }

}