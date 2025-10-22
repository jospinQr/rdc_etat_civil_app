package org.megamind.rdc_etat_civil_app.di.factory

import io.ktor.client.engine.HttpClientEngine

expect class HttpClientEngineFactory () {
    fun getHttpEngine(): HttpClientEngine
}