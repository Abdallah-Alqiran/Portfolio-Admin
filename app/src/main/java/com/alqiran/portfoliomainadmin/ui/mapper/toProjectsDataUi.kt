package com.alqiran.portfoliomainadmin.ui.mapper

import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.Project
import com.alqiran.portfoliomainadmin.ui.model.ProjectUiModel
import com.alqiran.portfoliomainadmin.ui.model.LinkUiModel

fun List<Project>.toProjectsDataUi(): List<ProjectUiModel> {
    return this.map { item ->
        ProjectUiModel(
            id = item.id,
            image = item.image,
            projectName = item.projectName,
            description = item.description,
            links = item.links.map { LinkUiModel(name = it.name, url = it.url) }
        )
    }
}