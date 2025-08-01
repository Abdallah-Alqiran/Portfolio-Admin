package com.alqiran.portfoliomainadmin.ui.screens.home_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alqiran.portfoliomainadmin.ui.components.TwoItemsPerRow
import com.alqiran.portfoliomainadmin.ui.model.VideoPresentationUiModel

@Composable
fun VideoPresentationsSection(videos: List<VideoPresentationUiModel>) {
    Column(modifier = Modifier.padding(horizontal = 8.dp)) {
        videos.forEach { video ->
            Box(
                modifier = Modifier.fillMaxWidth()
                    .clickable {
                        // TODO on click video URL
                    }
            ) {
                Text(
                    text = video.videoTitle,
                    color = MaterialTheme.colorScheme.tertiary,
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        }
    }
}
