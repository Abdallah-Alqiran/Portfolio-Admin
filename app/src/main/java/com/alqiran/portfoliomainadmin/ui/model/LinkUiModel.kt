package com.alqiran.portfoliomainadmin.ui.model

import kotlinx.serialization.Serializable

@Serializable
data class LinkUiModel(
    val name: String = "",
    val url: String = "",
)

