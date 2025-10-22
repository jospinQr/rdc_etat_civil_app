package org.megamind.rdc_etat_civil_app.data.repositoryImp

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import org.megamind.rdc_etat_civil_app.data.service.AuthService
import org.megamind.rdc_etat_civil_app.data.service.dto.AuthResponse
import org.megamind.rdc_etat_civil_app.data.service.dto.LoginRequest
import org.megamind.rdc_etat_civil_app.data.service.dto.RegisterRequest
import org.megamind.rdc_etat_civil_app.data.service.dto.UserInfoResponse
import org.megamind.rdc_etat_civil_app.domain.repository.AuthRepository
import org.megamind.rdc_etat_civil_app.utilis.Result

class AuthRepositoryImp(private val authService: AuthService) : AuthRepository {
    override suspend fun login(loginRequest: LoginRequest): Flow<Result<AuthResponse>> = flow {

        emit(Result.Loading)
        val result = authService.login(loginRequest)
        emit(result)

    }.catch {
        emit(Result.Error(it))

    }

    override suspend fun register(signupRequest: RegisterRequest): Flow<Result<AuthResponse>> =
        flow {

            emit(Result.Loading)
            val result = authService.register(signupRequest)
            emit(result)


        }.catch {
            emit(Result.Error(it))
        }



}