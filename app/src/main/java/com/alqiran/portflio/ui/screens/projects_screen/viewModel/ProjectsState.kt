package com.alqiran.portflio.ui.screens.projects_screen.viewModel

import com.alqiran.portflio.ui.model.ProjectUiModel

sealed class ProjectsState {
    object None: ProjectsState()
    object Loading: ProjectsState()

    data class Success(val projects: List<ProjectUiModel>): ProjectsState()
    data class Error(val error: String): ProjectsState()
}