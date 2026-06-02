package com.alqiran.portfoliomainadmin.data.datasourses.remote.model

import kotlinx.serialization.Serializable


@Serializable
data class PendingRecommendation(
    val id: String = "",
    val date: String = "",
    val userName: String = "",
    val email: String = "",
    val message: String = "",
)
