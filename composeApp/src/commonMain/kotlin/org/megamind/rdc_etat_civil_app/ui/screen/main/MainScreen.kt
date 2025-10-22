package org.megamind.rdc_etat_civil_app.ui.screen.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.auth0.jwt.JWT
import org.koin.compose.viewmodel.koinViewModel
import org.megamind.rdc_etat_civil_app.di.TokenManager
import org.megamind.rdc_etat_civil_app.ui.screen.auth.AuthViewModel

@Composable
fun MainScreen(viewModel: AuthViewModel = koinViewModel()) {


    val uiState by viewModel.authUiState.collectAsStateWithLifecycle()
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        val decodedJWT = JWT.decode(TokenManager.token)
        val role = decodedJWT.getClaim("role").asString()
        val provinceId = decodedJWT.getClaim("provinceId").asLong()
        val entiteId = decodedJWT.getClaim("entiteId").asLong()
        val communeId = decodedJWT.getClaim("communeId").asLong()
        Column {


            Text(role)
            Text(provinceId.toString())
            Text(entiteId.toString())
            Text(communeId.toString())

        }
    }

}