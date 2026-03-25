package com.alqiran.portfoliomainadmin.ui.screens.admin.video_admin.viewModel

import androidx.lifecycle.ViewModel
import com.alqiran.portfoliomainadmin.data.mapper.toVideoPresentation
import com.alqiran.portfoliomainadmin.data.mapper.toVideosPresentation
import com.alqiran.portfoliomainadmin.repository.FirebaseRepository
import com.alqiran.portfoliomainadmin.ui.model.VideoPresentationUiModel
import com.alqiran.portfoliomainadmin.ui.screens.admin.AdminState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class VideosAdminViewModel @Inject constructor(
    private val uploadRepo: FirebaseRepository
) : ViewModel() {
    private val _state = MutableStateFlow<AdminState>(AdminState.None)
    val state = _state.asStateFlow()

    fun uploadVideos(allVideos: List<VideoPresentationUiModel>) {
        _state.value = AdminState.Loading
        try {
            uploadRepo.uploadVideosPresentation(allVideos.toVideosPresentation())
            _state.value = AdminState.Success
        } catch (e: Exception) {
            _state.value = AdminState.Error(e.message.toString())
        }
    }

    fun deleteVideo(video: VideoPresentationUiModel) {
        _state.value = AdminState.Loading
        try {
            uploadRepo.deleteVideoPresentation(video.toVideoPresentation())
            _state.value = AdminState.Success
        } catch (e: Exception) {
            _state.value = AdminState.Error(e.message.toString())
        }
    }


    fun stateNone() {
        _state.value = AdminState.None
    }

}