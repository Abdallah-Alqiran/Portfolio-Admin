package com.alqiran.portfoliomainadmin.data.mapper

import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.Project
import com.alqiran.portfoliomainadmin.ui.model.ProjectUiModel

fun ProjectUiModel.toProject(): Project {
    return Project(
        id = this.id,
        image = this.image,
        projectName = this.projectName,
        description = this.description,
        githubUrl = this.githubUrl,
        googlePlayUrl = this.googlePlayUrl,
        appleStoreUrl = this.appleStoreUrl,
    )
    
}