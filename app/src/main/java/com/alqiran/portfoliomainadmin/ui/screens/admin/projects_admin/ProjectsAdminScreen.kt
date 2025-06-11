package com.alqiran.portfoliomainadmin.ui.screens.admin.projects_admin

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
import com.alqiran.portfoliomainadmin.ui.model.ProjectUiModel
import com.alqiran.portfoliomainadmin.ui.screens.admin.AdminState
import com.alqiran.portfoliomainadmin.ui.screens.admin.projects_admin.viewModel.ProjectsAdminViewModel
import com.alqiran.portfoliomainadmin.ui.utils.ButtonType

@Composable
fun ProjectAdminScreen(allProjects: List<ProjectUiModel>?) {

    val projectsAdminViewModel: ProjectsAdminViewModel = hiltViewModel()
    val projectsState by projectsAdminViewModel.state.collectAsStateWithLifecycle()

    val context = LocalContext.current

    when (projectsState) {
        is AdminState.Error -> {
            Toast.makeText(
                context,
                (projectsState as AdminState.Error).error,
                Toast.LENGTH_SHORT
            ).show()
            projectsAdminViewModel.stateNone()
        }

        AdminState.Loading -> {
        }

        AdminState.Success -> {
            Toast.makeText(context, "Data Saved Successful", Toast.LENGTH_SHORT).show()
            projectsAdminViewModel.stateNone()
        }
        AdminState.None -> Unit
    }

    var projects by remember { mutableStateOf(allProjects) }

    val listState = rememberLazyListState()
    LazyColumn(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .padding(horizontal = 8.dp),
        state = listState
    ) {
        items(projects?: emptyList()) { project ->
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                CustomOutlinedTextFieldWidget(
                    textValue = project.id.toString(),
                    textLabel = "ID",
                    placeHolderLabel = "Enter project id",
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                ) {
                    val newId = it.toIntOrNull() ?: project.id
                    projects = projects?.map { a ->
                        if (a == project) a.copy(id = newId) else a
                    }
                }
                CustomOutlinedTextFieldWidget(
                    textValue = project.projectName,
                    textLabel = "Project Name",
                    placeHolderLabel = "Enter Project Name",
                    modifier = Modifier
                        .weight(3f)
                ) {
                    projects = projects?.map { a ->
                        if (a == project) a.copy(projectName = it) else a
                    }
                }
            }

            CustomOutlinedTextFieldWidget(
                textValue = project.image,
                textLabel = "Project Image",
                placeHolderLabel = "Enter your Image URL"
            ) {
                projects = projects?.map { a ->
                    if (a == project) a.copy(image = it) else a
                }
            }

            CustomOutlinedTextFieldWidget(
                textValue = project.url,
                textLabel = "Project Link",
                placeHolderLabel = "Enter your Project URL"
            ) {
                projects = projects?.map { a ->
                    if (a == project) a.copy(url = it) else a
                }
            }

            CustomOutlinedTextFieldWidget(
                textValue = project.description,
                textLabel = "Project Link",
                placeHolderLabel = "Enter your Project URL",
                isSingleLine = false,
                minLines = 4
            ) {
                projects = projects?.map { a ->
                    if (a == project) a.copy(description = it) else a
                }
            }


            DeleteItemTextButton {
                projectsAdminViewModel.deleteEducation(project)
                projects = projects !!- project
            }
            Box(Modifier.padding(bottom = 16.dp))
        }

        item {
            AddItemTextButton {
                projects = (projects?: emptyList()) + ProjectUiModel(id = (projects?.size?: 0))
            }
        }

        item {
            DefaultButton(
                text = "Edit Projects",
                buttonType = ButtonType.UploadOnClick {
                    projectsAdminViewModel.uploadProjects(projects?: emptyList())
                }
            )
        }
    }
}