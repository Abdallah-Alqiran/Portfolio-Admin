package com.alqiran.portfoliomainadmin.ui.screens.home_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.alqiran.portfoliomainadmin.ui.model.ExperienceUiModel

@Composable
fun ExperienceSection(experiences: List<ExperienceUiModel>) {
    experiences.forEach { experience ->
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = experience.experienceTitle,
                color = MaterialTheme.colorScheme.tertiary,
                style = MaterialTheme.typography.headlineSmall
            )
            Text(
                text = experience.date,
                color = MaterialTheme.colorScheme.surfaceTint,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Text(
            text = experience.company,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.padding(4.dp)
        )

        Text(
            text = experience.description,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.surfaceTint,
            modifier = Modifier.padding(4.dp),
            maxLines = 5,
            overflow = TextOverflow.Ellipsis
        )

    }
}