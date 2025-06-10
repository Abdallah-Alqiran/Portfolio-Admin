package com.alqiran.portfoliomainadmin.data.mapper

import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.Education
import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.Project
import com.alqiran.portfoliomainadmin.ui.model.EducationUiModel
import com.alqiran.portfoliomainadmin.ui.model.ProjectUiModel

fun List<ProjectUiModel>.toProjects(): List<Project> {
    return this.map { item ->
        Project(
        id = item.id,
        image = item.image,
        projectName = item.projectName,
        description = item.description,
        url = item.url,
        )
    }
}