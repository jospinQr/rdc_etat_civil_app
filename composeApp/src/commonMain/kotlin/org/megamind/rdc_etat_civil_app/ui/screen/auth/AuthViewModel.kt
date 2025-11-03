package org.megamind.rdc_etat_civil_app.ui.screen.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.megamind.rdc_etat_civil_app.data.service.dto.LoginRequest
import org.megamind.rdc_etat_civil_app.data.service.dto.UserInfoResponse
import org.megamind.rdc_etat_civil_app.di.TokenManager
import org.megamind.rdc_etat_civil_app.domain.model.Role
import org.megamind.rdc_etat_civil_app.domain.repository.AuthRepository
import org.megamind.rdc_etat_civil_app.utilis.Result

class AuthViewModel(private val authRepository: AuthRepository) : ViewModel() {

    private val _authUiState = MutableStateFlow(AuthUiState())
    val authUiState = _authUiState.asStateFlow()

    private val _autUiEvent = MutableSharedFlow<AuthUiEvent>()
    val authUiEvent = _autUiEvent.asSharedFlow()


    fun onNameChange(name: String) {
        _authUiState.update { it.copy(name = name) }
    }

    fun onPasswordChange(password: String) {
        _authUiState.update { it.copy(password = password) }
    }

    fun onPassWordVisibilityChange() {
        _authUiState.update { it.copy(isPassWordVisible = !it.isPassWordVisible) }

    }

    fun onErrorDialogDismiss() {
        _authUiState.update { it.copy(errorMessage = null) }
    }

    fun onLogin() {

        val loginRequest = LoginRequest(authUiState.value.name, authUiState.value.password)

        viewModelScope.launch {

            authRepository.login(loginRequest).collect { result ->

                when (result) {

                    is Result.Loading -> {
                        _authUiState.update { it.copy(isLoading = true) }
                    }


                    is Result.Success -> {
                        _authUiState.update {
                            it.copy(
                                isLoading = false,
                                name = "",
                                password = "",

                                )
                        }
                        TokenManager.token = result.data.token
                        _autUiEvent.emit(AuthUiEvent.NavigateToMain)

                    }

                    is Result.Error -> {
                        _authUiState.update {
                            it.copy(
                                isLoading = false,
                                errorMessage = result.e?.message ?: "Erreur inconnue",
                            )
                        }
                    }
                }

            }

        }

    }


}


data class AuthUiState(
    val name: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isPassWordVisible: Boolean = false,
    val userInfo: UserInfoResponse = UserInfoResponse(username = "", role = Role.CB, 0, 0, 0)
)

sealed class AuthUiEvent() {

    object NavigateToMain : AuthUiEvent()

}