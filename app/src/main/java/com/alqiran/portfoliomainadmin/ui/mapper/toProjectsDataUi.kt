package com.alqiran.portfoliomainadmin.ui.mapper

import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.Project
import com.alqiran.portfoliomainadmin.ui.model.ProjectUiModel

fun List<Project>.toProjectsDataUi(): List<ProjectUiModel> {
    return this.map { item ->
        ProjectUiModel(
            id = item.id,
            image = item.image,
            projectName = item.projectName,
            description = item.description,
            githubUrl = item.githubUrl,
            googlePlayUrl = item.googlePlayUrl,
            appleStoreUrl = item.appleStoreUrl,
        )
    }
}