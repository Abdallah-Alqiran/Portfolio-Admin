package com.alqiran.portfoliomainadmin.ui.model

import kotlinx.serialization.Serializable


@Serializable
data class RecommendationUiModel(
    val id: String = "",
    val date: String = "",
    val order: Int?,
    val userName: String = "",
    val email: String = "",
    val message: String = "",
)
