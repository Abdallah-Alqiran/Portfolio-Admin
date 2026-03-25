package com.alqiran.portfoliomainadmin.ui.mapper

import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.Project
import com.alqiran.portfoliomainadmin.ui.model.ProjectUiModel

fun Project.toProjectItemDataUi(): ProjectUiModel {
    return ProjectUiModel(
        id = this.id,
        image = this.image,
        projectName = this.projectName,
        description = this.description,
        githubUrl = this.githubUrl,
        googlePlayUrl = this.googlePlayUrl,
        appleStoreUrl = this.appleStoreUrl
    )
}