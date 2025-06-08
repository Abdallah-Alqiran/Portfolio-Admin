package com.alqiran.portfoliomainadmin.ui.model

import kotlinx.serialization.Serializable

@Serializable
data class CourseUiModel (
    val id: Int = 0,
    val courseName: String = "",
    val courseDescription: String = "",
)