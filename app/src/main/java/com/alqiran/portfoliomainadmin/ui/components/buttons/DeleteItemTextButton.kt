package com.alqiran.portfoliomainadmin.ui.components.buttons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alqiran.portfoliomainadmin.ui.components.DeleteDialogWidget

@Composable
fun DeleteItemTextButton(text: String = "delete", onClick:() -> Unit) {
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        DeleteDialogWidget(onConfirm = {
            onClick()
        }) {
            showDialog = false
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 8.dp),
        horizontalArrangement = Arrangement.End
    ) {
        TextButton(onClick = {
            showDialog = true
        }) {
            Text(
                text,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}