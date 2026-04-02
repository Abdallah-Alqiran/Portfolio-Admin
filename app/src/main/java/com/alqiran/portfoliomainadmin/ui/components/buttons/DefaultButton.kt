package com.alqiran.portfoliomainadmin.ui.components.buttons

import android.content.Intent
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.alqiran.portfoliomainadmin.ui.utils.ButtonType

@Composable
fun DefaultButton(
    text: String,
    buttonType: ButtonType
) {
    val context = LocalContext.current

    Button(
        onClick = {
            when (buttonType) {
                is ButtonType.IntentNavigation -> {
                    val url = buttonType.url
                    if (Patterns.WEB_URL.matcher(url).matches()) {
                        context.startActivity(Intent(Intent.ACTION_VIEW, url.toUri()))
                    } else {
                        Toast.makeText(context, "Invalid URL", Toast.LENGTH_SHORT).show()
                    }
                }
                is ButtonType.ScreenNavigation -> {
                    buttonType.onNavigate(buttonType.navigationAction)
                }
                is ButtonType.UploadOnClick -> {
                    buttonType.onUpload()
                }
            }
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onBackground
        ),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .padding(horizontal = 4.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Bold
        )
    }
}