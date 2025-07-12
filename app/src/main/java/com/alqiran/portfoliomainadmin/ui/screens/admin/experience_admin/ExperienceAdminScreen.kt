package com.alqiran.portfoliomainadmin.ui.screens.admin.experience_admin

import android.util.Log
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alqiran.portfoliomainadmin.ui.components.CustomOutlinedTextFieldWidget
import com.alqiran.portfoliomainadmin.ui.components.buttons.AddItemTextButton
import com.alqiran.portfoliomainadmin.ui.components.buttons.DefaultButton
import com.alqiran.portfoliomainadmin.ui.components.buttons.DeleteItemTextButton
import com.alqiran.portfoliomainadmin.ui.model.ExperienceUiModel
import com.alqiran.portfoliomainadmin.ui.screens.admin.AdminState
import com.alqiran.portfoliomainadmin.ui.screens.admin.experience_admin.viewModel.ExperienceAdminViewModel
import com.alqiran.portfoliomainadmin.ui.utils.ButtonType

@Composable
fun ExperienceAdminScreen(allExperience: List<ExperienceUiModel>?) {

    val experienceAdminViewModel: ExperienceAdminViewModel = hiltViewModel()
    val experienceState by experienceAdminViewModel.state.collectAsStateWithLifecycle()

    val context = LocalContext.current

    when (experienceState) {
        is AdminState.Error -> {
            Toast.makeText(
                context,
                (experienceState as AdminState.Error).error,
                Toast.LENGTH_SHORT
            ).show()
            experienceAdminViewModel.stateNone()
        }

        AdminState.Loading -> {
        }

        AdminState.Success -> {
            Toast.makeText(context, "Data Saved Successful", Toast.LENGTH_SHORT).show()
            experienceAdminViewModel.stateNone()
        }
        AdminState.None -> Unit
    }

    var experiences by remember { mutableStateOf(allExperience) }

    Log.d("Al-qiran Ex", "ExperienceAdminScreen: $experiences")

    val listState = rememberLazyListState()
    LazyColumn(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .padding(horizontal = 8.dp),
        state = listState
    ) {
        items(experiences?: emptyList()) { experience ->
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                CustomOutlinedTextFieldWidget(
                    textValue = experience.id.toString(),
                    textLabel = "ID",
                    placeHolderLabel = "Enter experience id",
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                ) {
                    val newId = it.toIntOrNull() ?: experience.id
                    experiences = experiences?.map { a ->
                        if (a == experience) a.copy(id = newId) else a
                    }
                }
                CustomOutlinedTextFieldWidget(
                    textValue = experience.date,
                    textLabel = "date",
                    placeHolderLabel = "Enter the date",
                    modifier = Modifier
                        .weight(3f)
                ) {
                    experiences = experiences?.map { a ->
                        if (a == experience) a.copy(date = it) else a
                    }
                }
            }

            CustomOutlinedTextFieldWidget(
                textValue = experience.experienceTitle,
                textLabel = "Experience Title",
                placeHolderLabel = "Enter your Experience title"
            ) {
                experiences = experiences?.map { a ->
                    if (a == experience) a.copy(experienceTitle = it) else a
                }
            }

            CustomOutlinedTextFieldWidget(
                textValue = experience.company,
                textLabel = "Company",
                placeHolderLabel = "Enter Company name"
            ) {
                experiences = experiences?.map { a ->
                    if (a == experience) a.copy(company = it) else a
                }
            }
            CustomOutlinedTextFieldWidget(
                textValue = experience.description,
                textLabel = "Experience Description",
                placeHolderLabel = "Enter Description"
            ) {
                experiences = experiences?.map { a ->
                    if (a == experience) a.copy(description = it) else a
                }
            }

            DeleteItemTextButton {
                experienceAdminViewModel.deleteExperience(experience)
                experiences = experiences !!- experience
            }
            Box(Modifier.padding(bottom = 16.dp))
        }

        item {
            AddItemTextButton {
                experiences = (experiences?: emptyList()) + ExperienceUiModel(id = (experiences?.size?: 0))
            }
        }

        item {
            DefaultButton(
                text = "Edit Experience",
                buttonType = ButtonType.UploadOnClick {
                    experienceAdminViewModel.uploadExperience(experiences?: emptyList())
                }
            )
        }
    }
}