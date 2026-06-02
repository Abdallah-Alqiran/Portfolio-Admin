package com.alqiran.portfoliomainadmin.data.datasourses.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class Project(
    val id: Int = 0,
    val image: String = "",
    val projectName: String = "",
    val description: String = "",
    val links: List<Link> = emptyList(),
)
