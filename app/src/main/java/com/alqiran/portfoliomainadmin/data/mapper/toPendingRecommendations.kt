package com.alqiran.portfoliomainadmin.data.mapper

import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.PendingRecommendation
import com.alqiran.portfoliomainadmin.ui.model.PendingRecommendationUiModel

fun List<PendingRecommendationUiModel>.toPendingRecommendations(): List<PendingRecommendation> {
    return this.map { item ->
        PendingRecommendation(
            id = item.id,
            date = item.date,
            userName = item.userName,
            email = item.email,
            message = item.message
        )
    }
}