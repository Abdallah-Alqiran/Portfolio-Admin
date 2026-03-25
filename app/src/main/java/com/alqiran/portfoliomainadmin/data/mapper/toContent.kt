package com.alqiran.portfoliomainadmin.data.mapper

import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.Content
import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.Education
import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.Technology
import com.alqiran.portfoliomainadmin.ui.model.ContentUiModel
import com.alqiran.portfoliomainadmin.ui.model.EducationUiModel
import com.alqiran.portfoliomainadmin.ui.model.TechnologyUiModel

fun ContentUiModel.toContent(): Content {
    return Content(
        id = this.id,
        contentDescription = this.contentDescription,
        contentUrl = this.contentUrl,
    )
    
}