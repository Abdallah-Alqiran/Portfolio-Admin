package com.alqiran.portfoliomainadmin.ui.model

import kotlinx.serialization.Serializable


@Serializable
data class PendingRecommendationUiModel(
    val id: String = "",
    val date: String = "",
    val userName: String = "",
    val email: String = "",
    val message: String = "",
)
