package com.alqiran.portfoliomainadmin.ui.screens.home_screen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alqiran.portfoliomainadmin.ui.components.TwoItemsPerRow
import com.alqiran.portfoliomainadmin.ui.model.ContentTitleUiModel


@Composable
fun ContentsSection(
    contentsTitle: List<ContentTitleUiModel>
) {
    Column(modifier = Modifier.padding(horizontal = 8.dp)) {
        contentsTitle.forEach { contentTitle ->
            Text(
                text = contentTitle.contentTitle,
                color = MaterialTheme.colorScheme.tertiary,
                style = MaterialTheme.typography.headlineSmall
            )
            TwoItemsPerRow(
                items = contentTitle.contents.map { it.contentDescription },
                url = contentTitle.contents.map { it.contentUrl })
        }
    }
}