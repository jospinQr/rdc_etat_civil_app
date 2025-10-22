package org.megamind.rdc_etat_civil_app.data.service

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody

import org.megamind.rdc_etat_civil_app.data.service.dto.AuthResponse
import org.megamind.rdc_etat_civil_app.data.service.dto.LoginRequest
import org.megamind.rdc_etat_civil_app.data.service.dto.RegisterRequest
import org.megamind.rdc_etat_civil_app.data.service.dto.UserInfoResponse
import org.megamind.rdc_etat_civil_app.utilis.Result

class AuthService(private val httpClient: HttpClient) {


    suspend fun login(loginRequest: LoginRequest): Result<AuthResponse> {
        return safeApiCall<AuthResponse> {
            httpClient.post("auth/login") {
                setBody(loginRequest)
            }
        }

    }


    suspend fun register(signupRequest: RegisterRequest): Result<AuthResponse> {
        return safeApiCall<AuthResponse> {
            httpClient.post("auth/register") {
                setBody(signupRequest)
            }
        }
    }




}
