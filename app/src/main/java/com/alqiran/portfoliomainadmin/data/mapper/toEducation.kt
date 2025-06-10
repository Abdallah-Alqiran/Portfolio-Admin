package com.alqiran.portfoliomainadmin.data.mapper

import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.Education
import com.alqiran.portfoliomainadmin.ui.model.EducationUiModel

fun EducationUiModel.toEducation(): Education {
    return Education(
        id = this.id,
        university = this.university,
        date = this.date,
        major = this.major
    )
    
}