package com.alqiran.portfoliomainadmin.ui.model

import kotlinx.serialization.Serializable


@Serializable
data class CertificateUiModel(
    val id: Int = 0,
    val certificateName: String = "",
    val imageUrl: String = "",
    val description: String = "",
)
