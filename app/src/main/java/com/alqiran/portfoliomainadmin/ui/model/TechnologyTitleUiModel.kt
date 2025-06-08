package com.alqiran.portfoliomainadmin.ui.model


data class TechnologyTitleUiModel (
    val id: Int = 0,
    val technologyTitle: String = "",
    val technologies: List<TechnologyUiModel> = emptyList()
)