package org.megamind.rdc_etat_civil_app.ui.screen.auth

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.window.core.layout.WindowSizeClass
import androidx.window.core.layout.WindowWidthSizeClass
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.megamind.rdc_etat_civil_app.ui.screen.auth.component.AuthTextField
import org.megamind.yangu_stock.ui.components.ErrorDialog
import org.megamind.yangu_stock.ui.components.LoadinDialog
import rdc_etat_civil_app.composeapp.generated.resources.Res
import rdc_etat_civil_app.composeapp.generated.resources.compose_multiplatform

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    windowSizeClass: WindowSizeClass,
    onNavigateToMain: () -> Unit,
    viewModel: AuthViewModel = koinViewModel()

) {

    val uiState by viewModel.authUiState.collectAsStateWithLifecycle()
    val uiEvent = viewModel.authUiEvent

    LaunchedEffect(viewModel) {

        uiEvent.collect {
            when (it) {
                AuthUiEvent.NavigateToMain -> {
                    onNavigateToMain()
                }
            }
        }
    }

    LoginScreenContent(
        uiState = uiState,
        onNameChange = viewModel::onNameChange,
        onPasswordChange = viewModel::onPasswordChange,
        windowSizeClass = windowSizeClass,
        onLogin = { viewModel.onLogin() },
        onPassWordVisibilityChange = viewModel::onPassWordVisibilityChange,
        onErrorDialogDismiss = viewModel::onErrorDialogDismiss

    )

}

@Composable
fun LoginScreenContent(
    windowSizeClass: WindowSizeClass,
    uiState: AuthUiState,
    onNameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLogin: () -> Unit,
    onPassWordVisibilityChange: () -> Unit,
    onErrorDialogDismiss: () -> Unit
) {
    val isCompact = windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.COMPACT
    Scaffold { innerPadding ->
        Box(modifier = Modifier.fillMaxSize().imePadding(), contentAlignment = Alignment.Center) {

            Crossfade(
                targetState = isCompact,
                animationSpec = tween(durationMillis = 500, easing = LinearOutSlowInEasing)
            ) { isCompact ->

                if (isCompact) {

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Logo(size = 72.dp)
                        Spacer(modifier = Modifier.height(16.dp))
                        LoginCard(
                            uiState,
                            onNameChange,
                            onPasswordChange,

                            onLogin,
                            onPassWordVisibilityChange

                        )
                    }
                } else {
                    //Layout horizontal pour tablette/desktop
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Logo(168.dp)
                        Spacer(modifier = Modifier.width(32.dp))
                        LoginCard(
                            uiState,
                            onNameChange,
                            onPasswordChange,

                            onLogin,
                            onPassWordVisibilityChange

                        )
                    }
                }
            }

        }

        if (uiState.isLoading) {
            LoadinDialog()
        }

        if (uiState.errorMessage != null) {
            val message = uiState.errorMessage.ifBlank { "Nom ou mot de passe incorrecte" }
            ErrorDialog(
                showDialog = true,
                onDismissRequest = onErrorDialogDismiss,
                errorMessage = message
            )
        }


    }
}


@Composable
private fun Logo(size: Dp) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Text(
            "RDC ETAT CIVIL",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Authentification")
            Spacer(Modifier.width(12.dp))
            Icon(imageVector = Icons.Rounded.Lock, contentDescription = null)
        }


        Image(
            modifier = Modifier.size(size = size).clip(RoundedCornerShape(100)),
            painter = painterResource(Res.drawable.compose_multiplatform),
            contentDescription = null
        )

    }
}

@Composable
private fun LoginCard(
    uiState: AuthUiState,
    onNameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLogin: () -> Unit,
    onPassWordVisibilityChange: () -> Unit,

    ) {
    Card(modifier = Modifier.padding(horizontal = 8.dp)) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AuthTextField(
                value = uiState.name,
                onValueChange = onNameChange,
                label = "Nom d'utilisateur",
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Rounded.Person,
                        contentDescription = null
                    )
                }
            )
            Spacer(modifier = Modifier.height(12.dp))
            AuthTextField(
                value = uiState.password,
                onValueChange = onPasswordChange,
                label = "Mot de passe",
                visualTransformation = if (!uiState.isPassWordVisible) PasswordVisualTransformation(
                    mask = '*'
                ) else VisualTransformation.None,
                leadingIcon = { Icon(imageVector = Icons.Rounded.Lock, contentDescription = null) },
                trailingIcon = {
                    IconButton(onClick = { onPassWordVisibilityChange() }) {
                        Icon(
                            imageVector = if (!uiState.isPassWordVisible) Icons.Rounded.VisibilityOff else Icons.Rounded.Visibility,
                            contentDescription = null
                        )
                    }
                }
            )
            Spacer(modifier = Modifier.height(12.dp))
            Button(modifier = Modifier.fillMaxWidth(.5f), onClick = onLogin) {
                Text(text = "Login", color = MaterialTheme.colorScheme.onPrimary)
            }
            Spacer(modifier = Modifier.height(12.dp))


        }
    }


}