package com.alqiran.portfoliomainadmin.ui.screens.admin.education_admin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alqiran.portfoliomainadmin.ui.components.CustomOutlinedTextFieldWidget
import com.alqiran.portfoliomainadmin.ui.model.EducationUiModel

@Composable
fun EducationAdminScreen(allEducations: List<EducationUiModel>) {
    val listState = rememberLazyListState()


    var educations by remember { mutableStateOf(allEducations) }
    LazyColumn(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .padding(horizontal = 8.dp),
        state = listState
    ) {
        items(educations) { education ->
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                CustomOutlinedTextFieldWidget(
                    textValue = education.id.toString(),
                    textLabel = "ID",
                    placeHolderLabel = "Enter education id",
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                ) {
                    val newId = it.toIntOrNull() ?: education.id
                    educations = educations.map { a ->
                        if (a == education) a.copy(id = newId) else a
                    }
                }
                CustomOutlinedTextFieldWidget(
                    textValue = education.date,
                    textLabel = "date",
                    placeHolderLabel = "Enter the date",
                    modifier = Modifier
                        .weight(3f)
                ) {
                    educations = educations.map { a ->
                        if (a == education) a.copy(date = it) else a
                    }
                }
            }

            CustomOutlinedTextFieldWidget(
                textValue = education.university,
                textLabel = "University",
                placeHolderLabel = "Enter your University name"
            ) {
                educations = educations.map { a ->
                    if (a == education) a.copy(university = it) else a
                }
            }

            CustomOutlinedTextFieldWidget(
                textValue = education.major,
                textLabel = "major",
                placeHolderLabel = "Enter your University major"
            ) {
                educations = educations.map { a ->
                    if (a == education) a.copy(major = it) else a
                }
            }

            Box(Modifier.padding(bottom = 16.dp))
        }
    }
}