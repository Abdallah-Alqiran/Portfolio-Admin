package com.alqiran.portfoliomainadmin.data.mapper

import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.Project
import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.Link
import com.alqiran.portfoliomainadmin.ui.model.ProjectUiModel

fun List<ProjectUiModel>.toProjects(): List<Project> {
    return this.map { item ->
        Project(
            id = item.id,
            image = item.image,
            projectName = item.projectName,
            description = item.description,
            links = item.links.map { Link(name = it.name, url = it.url) }
        )
    }
}