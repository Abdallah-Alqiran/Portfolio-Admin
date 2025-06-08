package com.alqiran.portfoliomainadmin.ui.screens.home_screen.components

import android.content.Intent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.alqiran.portfoliomainadmin.ui.utils.ButtonType

@Composable
fun DefaultButton(
    text: String,
    buttonType: ButtonType
) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        val interactionSource = remember { MutableInteractionSource() }
        val isPressed by interactionSource.collectIsPressedAsState()

        val backgroundColor by animateColorAsState(
            label = "Button",
            targetValue = if (isPressed)
                MaterialTheme.colorScheme.secondaryContainer
            else MaterialTheme.colorScheme.primaryContainer
        )

        val scale: Float by animateFloatAsState(
            targetValue = if (isPressed) 0.95f else 1f,
            label = "Button"
        )

        val borderColor by animateColorAsState(
            targetValue = if (isPressed) MaterialTheme.colorScheme.primaryContainer else Color.Transparent,
            label = "Button"
        )

        val borderWidth by animateDpAsState(
            targetValue = if (isPressed) 3.dp else 0.dp,
            label = "Button"
        )

        val elevation = if (isPressed) 2.dp else 8.dp

        Button(
            onClick = {
                when (buttonType) {
                    is ButtonType.IntentNavigation -> {
                        context.startActivity(
                            Intent(
                                Intent.ACTION_VIEW,
                                (buttonType.url).toUri()
                            )
                        )
                    }
                    is ButtonType.ScreenNavigation -> {
                        buttonType.onNavigate(
                            buttonType.navigationAction
                        )
                    }
                    is ButtonType.MessageOnClick -> {
                        buttonType.onSendMessage()
                    }
                }
            },
            interactionSource = interactionSource,
            colors = ButtonDefaults.buttonColors(
                containerColor = backgroundColor,
                contentColor = MaterialTheme.colorScheme.primary
            ),
            shape = RoundedCornerShape(16.dp),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = elevation,
                pressedElevation = 0.dp,
                disabledElevation = 0.dp
            ),
            modifier = Modifier
                .padding(horizontal = 18.dp)
                .scale(scale)
                .border(borderWidth, borderColor, RoundedCornerShape(16.dp))
        ) {
            Text(text = text, style = MaterialTheme.typography.labelLarge)
        }
    }
}