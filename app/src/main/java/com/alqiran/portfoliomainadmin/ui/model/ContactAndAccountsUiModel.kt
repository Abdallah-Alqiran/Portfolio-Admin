package com.alqiran.portfoliomainadmin.ui.model

import kotlinx.serialization.Serializable

@Serializable
data class ContactAndAccountsUiModel (
    val id: Int = 0,
    val webName: String = "",
    val url: String = ""
)