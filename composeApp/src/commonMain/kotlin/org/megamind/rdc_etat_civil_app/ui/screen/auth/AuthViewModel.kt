package org.megamind.rdc_etat_civil_app.ui.screen.auth

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class AuthViewModel : ViewModel() {

    private val _authUiState = MutableStateFlow(AuthUiState())
    val authUiState: StateFlow<AuthUiState> = _authUiState


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


}


data class AuthUiState(
    val name: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isPassWordVisible: Boolean = false
)