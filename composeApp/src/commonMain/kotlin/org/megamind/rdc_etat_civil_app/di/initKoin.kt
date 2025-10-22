package org.megamind.rdc_etat_civil_app.di

import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.KoinAppDeclaration


fun initKoin(configuration: KoinAppDeclaration? = null) {

    startKoin {
        configuration?.invoke(this)
        modules(mainModule, ktorModule)
    }
}