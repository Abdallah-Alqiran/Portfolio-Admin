package com.alqiran.portfoliomainadmin.data.datasourses.remote.model

import kotlinx.serialization.Serializable


@Serializable
data class Recommendation(
val id: String = "",
val date: String = "",
val order: Int?,
val userName: String = "",
val email: String = "",
val message: String = "",
)
