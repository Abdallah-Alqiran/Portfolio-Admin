package com.alqiran.portfoliomainadmin.data.mapper

import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.Education
import com.alqiran.portfoliomainadmin.ui.model.EducationUiModel

fun List<EducationUiModel>.toEducations(): List<Education> {
    return this.map { item ->
        Education(
        id = item.id,
        university = item.university,
        date = item.date,
        major = item.major
        )
    }
}