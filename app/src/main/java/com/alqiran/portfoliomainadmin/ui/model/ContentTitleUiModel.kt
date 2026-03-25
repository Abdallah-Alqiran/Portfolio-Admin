package com.alqiran.portfoliomainadmin.ui.model

import kotlinx.serialization.Serializable

@Serializable
data class ContentTitleUiModel (
    val id: Int = 0,
    val contentTitle: String = "",
    val contents: List<ContentUiModel> = emptyList()
)