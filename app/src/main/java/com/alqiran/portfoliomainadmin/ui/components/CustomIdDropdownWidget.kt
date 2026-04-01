package com.alqiran.portfoliomainadmin.ui.components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomIdDropdownWidget(
    modifier: Modifier = Modifier,
    currentId: Int,
    allOtherIds: List<Int>,
    onSwap: (selectedId: Int) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    var inputText by remember(currentId) { mutableStateOf(currentId.toString()) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it },
        modifier = modifier
    ) {
        OutlinedTextField(
            value = inputText,
            onValueChange = { 
                if (it.all { char -> char.isDigit() }) {
                    inputText = it
                }
            },
            readOnly = false,
            label = { Text("ID") },
            placeholder = { Text("?") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier.menuAnchor(type = MenuAnchorType.PrimaryEditable, enabled = true),
            textStyle = MaterialTheme.typography.labelMedium,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    val newId = inputText.toIntOrNull()
                    if (newId != null && newId != currentId) {
                        onSwap(newId)
                    } else if (newId == null) {
                        inputText = currentId.toString()
                    }
                    expanded = false
                }
            ),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant,
                focusedLabelColor = MaterialTheme.colorScheme.primary,
                unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                cursorColor = MaterialTheme.colorScheme.primary,
                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                focusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                unfocusedPlaceholderColor = MaterialTheme.colorScheme.onSurfaceVariant,
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                disabledContainerColor = MaterialTheme.colorScheme.surface,
                errorContainerColor = MaterialTheme.colorScheme.errorContainer,
                selectionColors = TextSelectionColors(
                    handleColor = MaterialTheme.colorScheme.primary,
                    backgroundColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
                )
            )
        )
        
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
                inputText = currentId.toString()
            }
        ) {

            if (allOtherIds.isNotEmpty()) {
                allOtherIds.forEach { id ->
                    DropdownMenuItem(
                        text = { Text(id.toString()) },
                        onClick = {
                            inputText = id.toString()
                            onSwap(id)
                            expanded = false
                        }
                    )
                }
            } else {
                DropdownMenuItem(
                    text = { Text("?") },
                    onClick = { expanded = false },
                    enabled = false
                )
            }
        }
    }
}