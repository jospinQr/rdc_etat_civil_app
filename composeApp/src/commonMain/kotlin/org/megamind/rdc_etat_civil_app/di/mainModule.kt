package org.megamind.rdc_etat_civil_app.di

import androidx.lifecycle.viewmodel.compose.viewModel
import org.koin.core.annotation.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import org.megamind.rdc_etat_civil_app.data.repositoryImp.AuthRepositoryImp
import org.megamind.rdc_etat_civil_app.data.service.AuthService
import org.megamind.rdc_etat_civil_app.domain.repository.AuthRepository
import org.megamind.rdc_etat_civil_app.ui.screen.auth.AuthViewModel


val mainModule = module {

    single {
        AuthService(get())
    }

    single<AuthRepository> {
        AuthRepositoryImp(get())
    }

    viewModel {
        AuthViewModel(get())
    }

}