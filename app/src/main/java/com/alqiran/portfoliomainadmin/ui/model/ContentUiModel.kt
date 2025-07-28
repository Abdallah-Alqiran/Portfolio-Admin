package com.alqiran.portfoliomainadmin.ui.model

import kotlinx.serialization.Serializable

@Serializable
data class ContentUiModel (
    val id: Int = 0,
    val contentDescription: String = "",
    val contentUrl: String = ""
)
