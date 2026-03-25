package com.alqiran.portfoliomainadmin.ui.screens.home_screen.components

import android.content.Intent
import android.util.Patterns
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
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
            val context = LocalContext.current
            contentTitle.contents.forEach { content ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Box (
                        modifier = Modifier.fillMaxWidth()
                            .clickable{

                                val url = content.contentUrl
                                if (Patterns.WEB_URL.matcher(url).matches()) {
                                    context.startActivity(
                                        Intent(
                                            Intent.ACTION_VIEW,
                                            url.toUri()
                                        )
                                    )
                                }
                            }
                    ) {
                        Text(
                            text = content.contentDescription,
                            style = MaterialTheme.typography.labelLarge,
                            color = MaterialTheme.colorScheme.primary,
                        )
                    }
                }
            }
        }
    }
}