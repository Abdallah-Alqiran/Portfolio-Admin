package com.alqiran.portfoliomainadmin.ui.model

import kotlinx.serialization.Serializable

@Serializable
data class VideoPresentationUiModel(
    val id: Int = 0,
    val videoTitle: String = "",
    val videoUrl: String = ""
)
