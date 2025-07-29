package com.alqiran.portfoliomainadmin.ui.screens.admin.content_admin

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
import com.alqiran.portfoliomainadmin.ui.components.HeadlineTextWidget
import com.alqiran.portfoliomainadmin.ui.components.buttons.AddItemTextButton
import com.alqiran.portfoliomainadmin.ui.components.buttons.DefaultButton
import com.alqiran.portfoliomainadmin.ui.components.buttons.DeleteItemTextButton
import com.alqiran.portfoliomainadmin.ui.model.ContentTitleUiModel
import com.alqiran.portfoliomainadmin.ui.model.TechnologyTitleUiModel
import com.alqiran.portfoliomainadmin.ui.model.TechnologyUiModel
import com.alqiran.portfoliomainadmin.ui.screens.admin.AdminState
import com.alqiran.portfoliomainadmin.ui.screens.admin.content_admin.viewModel.ContentsAdminViewModel
import com.alqiran.portfoliomainadmin.ui.utils.ButtonType

@Composable
fun ContentsAdminScreen(allContentsTitle: List<ContentTitleUiModel>?) {
    val contentsViewModel: ContentsAdminViewModel = hiltViewModel()
    val contentsState by contentsViewModel.state.collectAsStateWithLifecycle()

    val context = LocalContext.current

    when (contentsState) {
        is AdminState.Error -> {
            Toast.makeText(
                context,
                (contentsState as AdminState.Error).error,
                Toast.LENGTH_SHORT
            ).show()
            contentsViewModel.stateNone()
        }

        AdminState.Loading -> {
        }

        AdminState.Success -> {
            Toast.makeText(context, "Data Saved Successful", Toast.LENGTH_SHORT).show()
            contentsViewModel.stateNone()
        }

        AdminState.None -> Unit
    }

    var technologyAndTools by remember { mutableStateOf(allContentsTitle) }

    val listState = rememberLazyListState()
    LazyColumn(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .padding(horizontal = 8.dp),
        state = listState
    ) {
        items(technologyAndTools ?: emptyList()) { technology ->
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                CustomOutlinedTextFieldWidget(
                    textValue = technology.id.toString(),
                    textLabel = "ID",
                    placeHolderLabel = "Enter technology id",
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                ) {
                    val newId = it.toIntOrNull() ?: technology.id
                    technologyAndTools = technologyAndTools?.map { a ->
                        if (a == technology) a.copy(id = newId) else a
                    }
                }
                CustomOutlinedTextFieldWidget(
                    textValue = technology.technologyTitle,
                    textLabel = "Technology Title",
                    placeHolderLabel = "Enter Technology",
                    modifier = Modifier
                        .weight(3f)
                ) {
                    technologyAndTools = technologyAndTools?.map { a ->
                        if (a == technology) a.copy(technologyTitle = it) else a
                    }
                }
            }

            HeadlineTextWidget("Technologies")

            AddItemTextButton {
                technologyAndTools = technologyAndTools?.map { title ->
                    if (title == technology) {
                        title.copy(
                            technologies = title.technologies + TechnologyUiModel(id = title.technologies.size)
                        )
                    } else title
                }
            }


            (technology.technologies).forEach { tech ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                ) {

                    CustomOutlinedTextFieldWidget(
                        textValue = tech.id.toString(),
                        textLabel = "id",
                        placeHolderLabel = "id",
                        modifier = Modifier.weight(1f)
                    ) {
                        technologyAndTools = technologyAndTools?.map { title ->
                            if (title == technology) {
                                title.copy(
                                    technologies = title.technologies.map { t ->
                                        if (t == tech)
                                            t.copy(id = it.toIntOrNull() ?: technology.id)
                                        else
                                            t
                                    }
                                )
                            } else title
                        }
                    }
                    CustomOutlinedTextFieldWidget(
                        textValue = tech.technologyName,
                        textLabel = "Technology Name",
                        placeHolderLabel = "Enter your Technology Name",
                        modifier = Modifier.weight(3f)
                    ) {
                        technologyAndTools = technologyAndTools?.map { title ->
                            if (title == technology) {
                                title.copy(
                                    technologies = title.technologies.map { t ->
                                        if (t == tech)
                                            t.copy(technologyName = it)
                                        else
                                            t
                                    }
                                )
                            } else title
                        }
                    }

                }
                DeleteItemTextButton {
                    contentsViewModel.deleteTechnology(tech)
                    technologyAndTools = technologyAndTools?.map { title ->
                        if (title == technology) {
                            title.copy(technologies = title.technologies - tech)
                        } else title
                    }
                }

            }

            DeleteItemTextButton(
                text = "Delete Full Technology"
            ) {
                contentsViewModel.deleteTechnologiesAndTools(technology)
                technologyAndTools = technologyAndTools!! - technology
            }
            Box(Modifier.padding(bottom = 16.dp))
        }

        item {
            AddItemTextButton (
                text = "Add technology with Title"
            ){
                technologyAndTools = (technologyAndTools
                    ?: emptyList()) + TechnologyTitleUiModel(id = (technologyAndTools?.size ?: 0))
            }
        }

        item {
            DefaultButton(
                text = "Edit Technologies",
                buttonType = ButtonType.UploadOnClick {
                    contentsViewModel.uploadTechnologiesAndTools(
                        technologyAndTools ?: emptyList()
                    )
                }
            )
        }
    }
}