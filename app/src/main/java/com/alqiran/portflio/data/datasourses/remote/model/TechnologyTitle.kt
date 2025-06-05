package com.alqiran.portflio.data.datasourses.remote.model


data class TechnologyTitle (
    val id: Int = 0,
    val technologyTitle: String = "",
    val technologies: List<Technology> = emptyList()
)