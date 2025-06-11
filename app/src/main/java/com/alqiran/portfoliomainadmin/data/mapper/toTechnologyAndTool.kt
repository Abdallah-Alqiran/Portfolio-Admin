package com.alqiran.portfoliomainadmin.data.mapper

import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.Technology
import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.TechnologyTitle
import com.alqiran.portfoliomainadmin.ui.model.TechnologyTitleUiModel

fun TechnologyTitleUiModel.toTechnologyAndTool(): TechnologyTitle {
    return TechnologyTitle(
        id = this.id,
        technologyTitle = this.technologyTitle,
        technologies = this.technologies.map { tech ->
            Technology(
                id = tech.id,
                technologyName = tech.technologyName
            )
        },
    )
}