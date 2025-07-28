package com.alqiran.portfoliomainadmin.data.datasourses.remote.model


data class ContentTitle (
    val id: Int = 0,
    val contentTitle: String = "",
    val contents: List<Content> = emptyList()
)