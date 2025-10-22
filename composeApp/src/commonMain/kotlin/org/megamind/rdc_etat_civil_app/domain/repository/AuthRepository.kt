package org.megamind.rdc_etat_civil_app.domain.repository

import kotlinx.coroutines.flow.Flow
import org.megamind.rdc_etat_civil_app.data.service.dto.AuthResponse
import org.megamind.rdc_etat_civil_app.data.service.dto.LoginRequest
import org.megamind.rdc_etat_civil_app.data.service.dto.RegisterRequest
import org.megamind.rdc_etat_civil_app.data.service.dto.UserInfoResponse
import org.megamind.rdc_etat_civil_app.utilis.Result


interface AuthRepository {

    suspend fun login(loginRequest: LoginRequest): Flow<Result<AuthResponse>>

    suspend fun register(signupRequest: RegisterRequest): Flow<Result<AuthResponse>>




}