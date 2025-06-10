package com.alqiran.portfoliomainadmin.ui.screens.admin.education_admin

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.alqiran.portfoliomainadmin.ui.components.CustomOutlinedTextFieldWidget
import com.alqiran.portfoliomainadmin.ui.components.buttons.AddItemTextButton
import com.alqiran.portfoliomainadmin.ui.components.buttons.DefaultButton
import com.alqiran.portfoliomainadmin.ui.model.EducationUiModel
import com.alqiran.portfoliomainadmin.ui.screens.admin.AdminState
import com.alqiran.portfoliomainadmin.ui.screens.admin.education_admin.viewModel.EducationAdminViewModel
import com.alqiran.portfoliomainadmin.ui.utils.ButtonType
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alqiran.portfoliomainadmin.ui.components.buttons.DeleteItemTextButton


@Composable
fun EducationAdminScreen(allEducations: List<EducationUiModel>?) {
    val listState = rememberLazyListState()

    val educationAdminViewModel: EducationAdminViewModel = hiltViewModel()
    val educationState by educationAdminViewModel.educationAdminState.collectAsStateWithLifecycle()

    val context = LocalContext.current

    when (educationState) {
        is AdminState.Error -> {
            Toast.makeText(
                context,
                (educationState as AdminState.Error).error,
                Toast.LENGTH_SHORT
            ).show()
            educationAdminViewModel.stateNone()
        }

        AdminState.Loading -> {
        }

        AdminState.Success -> {
            Toast.makeText(context, "Data Saved Successful", Toast.LENGTH_SHORT).show()
            educationAdminViewModel.stateNone()
        }
        AdminState.None -> Unit
    }

    var educations by remember { mutableStateOf(allEducations) }
    LazyColumn(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .padding(horizontal = 8.dp),
        state = listState
    ) {
        items(educations?: emptyList()) { education ->
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
                    educations = educations?.map { a ->
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
                    educations = educations?.map { a ->
                        if (a == education) a.copy(date = it) else a
                    }
                }
            }

            CustomOutlinedTextFieldWidget(
                textValue = education.university,
                textLabel = "University",
                placeHolderLabel = "Enter your University name"
            ) {
                educations = educations?.map { a ->
                    if (a == education) a.copy(university = it) else a
                }
            }

            CustomOutlinedTextFieldWidget(
                textValue = education.major,
                textLabel = "major",
                placeHolderLabel = "Enter your University major"
            ) {
                educations = educations?.map { a ->
                    if (a == education) a.copy(major = it) else a
                }
            }
            DeleteItemTextButton {
                educationAdminViewModel.deleteEducation(education)
                educations = educations !!- education
            }
            Box(Modifier.padding(bottom = 16.dp))
        }

        item {
            AddItemTextButton {
                educations = (educations?: emptyList()) + EducationUiModel(id = (educations?.size?: 0))
            }
        }

        item {
            DefaultButton(
                text = "Edit Education",
                buttonType = ButtonType.UploadOnClick {
                    educationAdminViewModel.uploadEducationData(educations?: emptyList())
                }
            )
        }
    }
}