package com.alqiran.portflio.ui.screens.project_item_screen.viewModel

import com.alqiran.portflio.ui.model.ProjectUiModel


sealed class ProjectItemState {
    object None: ProjectItemState()
    object Loading: ProjectItemState()

    data class Success(val project: ProjectUiModel): ProjectItemState()
    data class Error(val error: String): ProjectItemState()
}