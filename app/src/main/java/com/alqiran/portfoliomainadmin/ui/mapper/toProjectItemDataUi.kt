package com.alqiran.portfoliomainadmin.ui.mapper

import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.Project
import com.alqiran.portfoliomainadmin.ui.model.ProjectUiModel
import com.alqiran.portfoliomainadmin.ui.model.LinkUiModel

fun Project.toProjectItemDataUi(): ProjectUiModel {
    return ProjectUiModel(
        id = this.id,
        image = this.image,
        projectName = this.projectName,
        description = this.description,
        links = this.links.map { LinkUiModel(name = it.name, url = it.url) }
    )
}