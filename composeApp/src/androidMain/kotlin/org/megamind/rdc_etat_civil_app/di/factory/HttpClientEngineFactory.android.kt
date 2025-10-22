package org.megamind.rdc_etat_civil_app.di.factory

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp

actual class HttpClientEngineFactory actual constructor() {
    actual fun getHttpEngine(): HttpClientEngine {
        return  OkHttp.create()

    }
}