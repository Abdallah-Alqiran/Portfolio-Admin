package com.alqiran.portfoliomainadmin.ui.components.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.RemoveRedEye
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.alqiran.portfoliomainadmin.ui.navigation.NavigationAction

@Composable
fun DefaultTextButton(
    onNavigate: (NavigationAction) -> Unit,
    navigateAction: NavigationAction,
    modifier: Modifier = Modifier,
    text: String = "Edit",
    isToEdit: Boolean = true,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(if (isToEdit) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
            .clickable { onNavigate(navigateAction) }
            .padding(horizontal = 12.dp, vertical = 6.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = if (isToEdit) Icons.Rounded.Edit else Icons.Rounded.RemoveRedEye,
                contentDescription = null,
                modifier = Modifier.size(14.dp),
                tint = if (isToEdit) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = if (isToEdit) {
                    if (text.isEmpty()) "Edit" else "Edit $text"
                } else {
                    if (text.isEmpty()) "View" else "View $text"
                },
                style = MaterialTheme.typography.labelMedium,
                color = if (isToEdit) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
            )
        }
    }
}
