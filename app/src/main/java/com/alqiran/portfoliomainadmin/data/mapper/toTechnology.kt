package com.alqiran.portfoliomainadmin.data.mapper

import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.Education
import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.Technology
import com.alqiran.portfoliomainadmin.ui.model.EducationUiModel
import com.alqiran.portfoliomainadmin.ui.model.TechnologyUiModel

fun TechnologyUiModel.toTechnology(): Technology {
    return Technology(
        id = this.id,
        technologyName = this.technologyName,
    )
    
}