package com.alqiran.portfoliomainadmin.ui.model

import kotlinx.serialization.Serializable

@Serializable
data class ExperienceUiModel(
    val experienceTitle: String = "",
    val company: String = "",
    val date: String = "",
    val description: String = ""
)
