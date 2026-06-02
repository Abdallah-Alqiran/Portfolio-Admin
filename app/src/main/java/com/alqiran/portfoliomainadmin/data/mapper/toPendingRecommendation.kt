package com.alqiran.portfoliomainadmin.data.mapper

import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.PendingRecommendation
import com.alqiran.portfoliomainadmin.ui.model.PendingRecommendationUiModel

fun PendingRecommendationUiModel.toPendingRecommendation(): PendingRecommendation {
    return PendingRecommendation(
        id = this.id,
        date = this.date,
        userName = this.userName,
        email = this.email,
        message = this.message
    )
}