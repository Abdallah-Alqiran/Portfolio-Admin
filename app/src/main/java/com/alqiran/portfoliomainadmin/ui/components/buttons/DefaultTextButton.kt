package com.alqiran.portfoliomainadmin.ui.components.buttons

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.alqiran.portfoliomainadmin.ui.navigation.NavigationAction

@Composable
fun DefaultTextButton(
    text: String = "Edit",
    onNavigate: (NavigationAction) -> Unit,
    navigateAction: NavigationAction,
    color: Color = MaterialTheme.colorScheme.primaryContainer
) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        TextButton(
            onClick = {
                onNavigate(navigateAction)
            },
        ) {
            Text(
                "View All $text to Edit",
                style = MaterialTheme.typography.labelSmall,
                color = color,
            )
        }
    }
}