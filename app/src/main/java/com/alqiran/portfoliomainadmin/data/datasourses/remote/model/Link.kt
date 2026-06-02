package com.alqiran.portfoliomainadmin.data.datasourses.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class Link(
    val name: String = "",
    val url: String = "",
)

