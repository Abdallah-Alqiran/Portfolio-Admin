package com.alqiran.portfoliomainadmin.data.mapper

import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.VideoPresentation
import com.alqiran.portfoliomainadmin.ui.model.VideoPresentationUiModel

fun List<VideoPresentationUiModel>.toVideosPresentation(): List<VideoPresentation> {
    return this.map { item ->
        VideoPresentation(
            id = item.id,
            videoTitle = item.videoTitle,
            videoUrl = item.videoUrl
        )
    }
}