package com.alqiran.portfoliomainadmin.ui.screens.home_screen.components


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alqiran.portfoliomainadmin.ui.components.TwoItemsPerRow
import com.alqiran.portfoliomainadmin.ui.model.TechnologyTitleUiModel

@Composable
fun TechnologiesAndToolsSection(
    technologiesAndTools: List<TechnologyTitleUiModel>
) {
    Column(modifier = Modifier.padding(horizontal = 8.dp)) {
        technologiesAndTools.forEach { technologyTitleItem ->
            Text(
                text = technologyTitleItem.technologyTitle,
                color = MaterialTheme.colorScheme.tertiary,
                style = MaterialTheme.typography.headlineSmall
            )
            TwoItemsPerRow(items = technologyTitleItem.technologies.map { it.technologyName })
        }
    }
}
