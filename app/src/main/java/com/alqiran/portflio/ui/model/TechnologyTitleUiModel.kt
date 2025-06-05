package com.alqiran.portflio.ui.model


data class TechnologyTitleUiModel (
    val id: Int = 0,
    val technologyTitle: String = "",
    val technologies: List<TechnologyUiModel> = emptyList()
)