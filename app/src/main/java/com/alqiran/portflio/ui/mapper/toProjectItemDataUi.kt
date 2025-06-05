package com.alqiran.portflio.ui.mapper

import com.alqiran.portflio.data.datasourses.remote.model.Project
import com.alqiran.portflio.ui.model.ProjectUiModel

fun Project.toProjectItemDataUi(): ProjectUiModel {
    return ProjectUiModel(
        id = this.id,
        image = this.image,
        projectName = this.projectName,
        description = this.description,
        url = this.url
    )
}