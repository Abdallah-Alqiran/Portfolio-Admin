package com.alqiran.portflio.ui.mapper

import com.alqiran.portflio.data.datasourses.remote.model.Project
import com.alqiran.portflio.ui.model.ProjectUiModel

fun List<Project>.toProjectsDataUi(): List<ProjectUiModel> {
    return this.map { item ->
        ProjectUiModel(
            id = item.id,
            image = item.image,
            projectName = item.projectName,
            description = item.description,
            url = item.url
        )
    }
}