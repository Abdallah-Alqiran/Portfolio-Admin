package com.alqiran.portfoliomainadmin.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alqiran.portfoliomainadmin.ui.components.buttons.DefaultTextButton
import com.alqiran.portfoliomainadmin.ui.navigation.NavigationAction

@Composable
fun SectionHeaderWithEdit(
    title: String,
    onNavigate: (NavigationAction) -> Unit,
    editAction: NavigationAction,
    secondActionText: String? = null,
    onSecondAction: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        HeadlineTextWidget(
            text = title,
            modifier = Modifier.weight(1f)
        )
        
        Spacer(modifier = Modifier.width(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (secondActionText != null && onSecondAction != null) {
                DefaultTextButton(
                    text = secondActionText,
                    isToEdit = false,
                    onNavigate = { onSecondAction() },
                    navigateAction = NavigationAction.ToAboutEdit("")
                )
            }

            DefaultTextButton(
                text = "",
                onNavigate = onNavigate,
                navigateAction = editAction
            )
        }
    }
}
