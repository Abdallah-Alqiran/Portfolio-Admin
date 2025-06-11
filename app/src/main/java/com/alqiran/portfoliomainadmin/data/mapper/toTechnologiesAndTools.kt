package com.alqiran.portfoliomainadmin.data.mapper

import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.Education
import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.Project
import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.Technology
import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.TechnologyTitle
import com.alqiran.portfoliomainadmin.ui.model.EducationUiModel
import com.alqiran.portfoliomainadmin.ui.model.ProjectUiModel
import com.alqiran.portfoliomainadmin.ui.model.TechnologyTitleUiModel

fun List<TechnologyTitleUiModel>.toTechnologiesAndTools(): List<TechnologyTitle> {
    return this.map { item ->
        TechnologyTitle(
        id = item.id,
        technologyTitle = item.technologyTitle,
        technologies = item.technologies.map { tech ->
            Technology(
                id = tech.id,
                technologyName = tech.technologyName
            )
        },
        )
    }
}