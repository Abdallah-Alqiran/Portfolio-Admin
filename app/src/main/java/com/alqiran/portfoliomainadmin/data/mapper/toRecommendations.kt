package com.alqiran.portfoliomainadmin.data.mapper

import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.Recommendation
import com.alqiran.portfoliomainadmin.ui.model.RecommendationUiModel

fun List<RecommendationUiModel>.toRecommendations(): List<Recommendation> {
    return this.map { item ->
        Recommendation(
            id = item.id,
            date = item.date,
            order = item.order,
            userName = item.userName,
            email = item.email,
            message = item.message
        )
    }
}