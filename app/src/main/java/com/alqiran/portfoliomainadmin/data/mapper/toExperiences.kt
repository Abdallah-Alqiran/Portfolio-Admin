package com.alqiran.portfoliomainadmin.data.mapper

import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.Education
import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.Experience
import com.alqiran.portfoliomainadmin.ui.model.EducationUiModel
import com.alqiran.portfoliomainadmin.ui.model.ExperienceUiModel

fun List<ExperienceUiModel>.toExperiences(): List<Experience> {
    return this.map { item ->
        Experience(
            id = item.id,
            experienceTitle = item.experienceTitle,
            company = item.company,
            date = item.date,
            description = item.description
        )
    }
}