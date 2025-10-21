package org.megamind.yangu_stock.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


@Composable
fun LoadinDialog(modifier: Modifier = Modifier, text: String = "Chargement") {


    AlertDialog(
        containerColor = MaterialTheme.colorScheme.background,
        onDismissRequest = {},
        confirmButton = {},
        text = {

            Row(verticalAlignment = Alignment.CenterVertically) {

                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.onBackground
                )
                Spacer(Modifier.width(22.dp))
                Text(
                    "$text ...",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    )

}

