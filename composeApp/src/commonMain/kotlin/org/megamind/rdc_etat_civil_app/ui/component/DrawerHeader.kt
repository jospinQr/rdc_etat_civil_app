package org.megamind.rdc_etat_civil_app.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import org.megamind.rdc_etat_civil_app.utilis.StringValues
import rdc_etat_civil_app.composeapp.generated.resources.Res
import rdc_etat_civil_app.composeapp.generated.resources.congo
import rdc_etat_civil_app.composeapp.generated.resources.lvde
import rdc_etat_civil_app.composeapp.generated.resources.sos


@Composable
fun DrawerHeader(modifier: Modifier = Modifier, userName: String = "", userRole: String = "") {


    Column(modifier = modifier) {
        // En-tête avec logo
        Text(
            text = StringValues.appName,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row {

            Image(
                modifier = Modifier
                    .size(78.dp)
                    .clip(RoundedCornerShape(100)),
                painter = painterResource(Res.drawable.sos),
                contentDescription = null
            )

        }
        Spacer(modifier = Modifier.height(8.dp))
        Row {

            Text(
                userName,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            )
            Text(
                userRole,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            )

        }


        Divider(modifier = Modifier.padding(vertical = 8.dp))
    }

}