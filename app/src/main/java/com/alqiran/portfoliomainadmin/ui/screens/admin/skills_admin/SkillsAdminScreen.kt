package com.alqiran.portfoliomainadmin.ui.screens.admin.skills_admin

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
import com.alqiran.portfoliomainadmin.ui.model.SkillUiModel
import com.alqiran.portfoliomainadmin.ui.screens.admin.AdminState
import com.alqiran.portfoliomainadmin.ui.screens.admin.skills_admin.viewModel.SkillsAdminViewModel
import com.alqiran.portfoliomainadmin.ui.utils.ButtonType

@Composable
fun SkillsAdminScreen(allSkills: List<SkillUiModel>?) {


    val skillsAdminViewModel: SkillsAdminViewModel = hiltViewModel()
    val skillsState by skillsAdminViewModel.state.collectAsStateWithLifecycle()

    val context = LocalContext.current

    when (skillsState) {
        is AdminState.Error -> {
            Toast.makeText(
                context,
                (skillsState as AdminState.Error).error,
                Toast.LENGTH_SHORT
            ).show()
            skillsAdminViewModel.stateNone()
        }

        AdminState.Loading -> {
        }

        AdminState.Success -> {
            Toast.makeText(context, "Data Saved Successful", Toast.LENGTH_SHORT).show()
            skillsAdminViewModel.stateNone()
        }
        AdminState.None -> Unit
    }

    var skills by remember { mutableStateOf(allSkills) }

    val listState = rememberLazyListState()
    LazyColumn(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .padding(horizontal = 8.dp),
        state = listState
    ) {
        items(skills?: emptyList()) { skill ->
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                CustomOutlinedTextFieldWidget(
                    textValue = skill.id.toString(),
                    textLabel = "ID",
                    placeHolderLabel = "Enter skill id",
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                ) {
                    val newId = it.toIntOrNull() ?: skill.id
                    skills = skills?.map { a ->
                        if (a == skill) a.copy(id = newId) else a
                    }
                }
                CustomOutlinedTextFieldWidget(
                    textValue = skill.skillName,
                    textLabel = "Skill",
                    placeHolderLabel = "Enter Skill",
                    modifier = Modifier
                        .weight(3f)
                ) {
                    skills = skills?.map { a ->
                        if (a == skill) a.copy(skillName = it) else a
                    }
                }
            }


            DeleteItemTextButton {
                skillsAdminViewModel.deleteSkill(skill)
                skills = skills !!- skill
            }
            Box(Modifier.padding(bottom = 16.dp))
        }

        item {
            AddItemTextButton {
                skills = (skills?: emptyList()) + SkillUiModel(id = (skills?.size?: 0))
            }
        }

        item {
            DefaultButton(
                text = "Edit Skills",
                buttonType = ButtonType.UploadOnClick {
                    skillsAdminViewModel.uploadSkills(skills?: emptyList())
                }
            )
        }
    }
}