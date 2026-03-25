package com.alqiran.portfoliomainadmin.data.mapper

import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.Project
import com.alqiran.portfoliomainadmin.ui.model.ProjectUiModel

fun List<ProjectUiModel>.toProjects(): List<Project> {
    return this.map { item ->
        Project(
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