package org.megamind.yangu_stock.ui.components

import androidx.compose.material3.*
import androidx.compose.runtime.*

@Composable
fun ErrorDialog(
    showDialog: Boolean,
    onDismissRequest: () -> Unit,
    errorMessage: String
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = onDismissRequest,
            title = {
                Text(text = "Erreur")
            },
            text = {
                Text(text = errorMessage,color = MaterialTheme.colorScheme.error)
            },
            confirmButton = {
                TextButton(onClick = onDismissRequest) {
                    Text("OK")
                }
            }
        )
    }
}