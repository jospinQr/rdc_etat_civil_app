package org.megamind.rdc_etat_civil_app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.megamind.rdc_etat_civil_app.di.initKoin

class MyApp : Application() {


    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@MyApp)

        }
    }
}