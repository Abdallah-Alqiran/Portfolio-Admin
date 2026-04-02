package com.alqiran.portfoliomainadmin.ui.screens.home_screen.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun AboutSection(userAbout: String) {
    Text(
        text = userAbout,
        color = MaterialTheme.colorScheme.onSurface,
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier.padding(horizontal = 4.dp),
        textAlign = TextAlign.Start
    )
}
