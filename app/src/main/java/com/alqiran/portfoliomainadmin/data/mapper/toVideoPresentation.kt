package com.alqiran.portfoliomainadmin.data.mapper

import com.alqiran.portfoliomainadmin.data.datasourses.remote.model.VideoPresentation
import com.alqiran.portfoliomainadmin.ui.model.VideoPresentationUiModel

fun VideoPresentationUiModel.toVideoPresentation(): VideoPresentation {
    return VideoPresentation(
        id = this.id,
        videoTitle = this.videoTitle,
        videoUrl = this.videoUrl
    )
}