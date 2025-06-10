package com.alqiran.portfoliomainadmin.ui.screens.admin.projects_admin.viewModel

import androidx.lifecycle.ViewModel
import com.alqiran.portfoliomainadmin.data.mapper.toProject
import com.alqiran.portfoliomainadmin.data.mapper.toProjects
import com.alqiran.portfoliomainadmin.repository.FirebaseRepository
import com.alqiran.portfoliomainadmin.ui.model.ProjectUiModel
import com.alqiran.portfoliomainadmin.ui.screens.admin.AdminState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ProjectsAdminViewModel @Inject constructor(
    private val uploadRepo: FirebaseRepository
) : ViewModel() {
    private val _state = MutableStateFlow<AdminState>(AdminState.None)
    val state = _state.asStateFlow()

    fun uploadProjects(allProjects: List<ProjectUiModel>) {
        _state.value = AdminState.Loading
        try {
            uploadRepo.uploadProjects(allProjects.toProjects())
            _state.value = AdminState.Success
        } catch (e: Exception) {
            _state.value = AdminState.Error(e.message.toString())
        }
    }

    fun deleteEducation(project: ProjectUiModel) {
        _state.value = AdminState.Loading
        try {
            uploadRepo.deleteProject(project.toProject())
            _state.value = AdminState.Success
        } catch (e: Exception) {
            _state.value = AdminState.Error(e.message.toString())
        }
    }


    fun stateNone() {
        _state.value = AdminState.None
    }

}