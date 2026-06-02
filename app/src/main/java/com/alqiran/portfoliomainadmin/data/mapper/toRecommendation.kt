package com.alqiran.portfoliomainadmin.data.mapper

import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.Recommendation
import com.alqiran.portfoliomainadmin.ui.model.RecommendationUiModel

fun RecommendationUiModel.toRecommendation(): Recommendation {
    return Recommendation(
        id = this.id,
        date = this.date,
        order = this.order,
        userName = this.userName,
        email = this.email,
        message = this.message
    )
}