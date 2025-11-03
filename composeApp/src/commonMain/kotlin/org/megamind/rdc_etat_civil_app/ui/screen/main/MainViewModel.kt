package org.megamind.rdc_etat_civil_app.ui.screen.main

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AdminMainUiState())
    val uiState = _uiState.asStateFlow()

    fun setNavBarItemIndex(index: Int) {

        _uiState.update { it.copy(navBarItemIndex = index) }
    }


}


data class AdminMainUiState(

    val navBarItemIndex: Int = 0,

    )