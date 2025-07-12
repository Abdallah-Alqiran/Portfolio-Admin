package com.alqiran.portfoliomainadmin.data.mapper

import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.Experience
import com.alqiran.portfoliomainadmin.ui.model.ExperienceUiModel

fun ExperienceUiModel.toExperience(): Experience {
    return Experience(
        id = this.id,
        experienceTitle = this.experienceTitle,
        company = this.company,
        date = this.date,
        description = this.description
    )

}