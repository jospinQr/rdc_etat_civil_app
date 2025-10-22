package org.megamind.rdc_etat_civil_app.di

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import org.megamind.rdc_etat_civil_app.di.factory.HttpClientEngineFactory


val ktorModule = module {

    single<HttpClient> {
        HttpClient(get()) {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true

                })
            }
            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL
            }

            defaultRequest {
                //  url("http://192.168.1.3:8080/api/v1/")
                url("http://localhost:8082/api/v1/")
                header(HttpHeaders.ContentType, ContentType.Application.Json)

                TokenManager.token?.let { token ->
                    header(HttpHeaders.Authorization, "Bearer $token")
                }
            }
        }
    }


    // crée une nouvelle instance de HttpClientEngine à chaque fois qu'elle est demandée.
    factory<HttpClientEngine> {
        HttpClientEngineFactory().getHttpEngine()
    }
}

object TokenManager {
    var token: String? = null
}