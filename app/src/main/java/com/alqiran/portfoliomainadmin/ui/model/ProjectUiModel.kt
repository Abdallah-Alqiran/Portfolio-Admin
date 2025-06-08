package com.alqiran.portfoliomainadmin.ui.model

import kotlinx.serialization.Serializable

@Serializable
data class ProjectUiModel(
    val id: Int = 0,
    val image: String = "",
    val projectName: String = "",
    val description: String = "",
    val url: String = ""
)
