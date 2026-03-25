package com.alqiran.portfoliomainadmin.ui.screens.admin.content_admin.viewModel

import androidx.lifecycle.ViewModel
import com.alqiran.portfoliomainadmin.data.mapper.toContent
import com.alqiran.portfoliomainadmin.data.mapper.toContentAndTitle
import com.alqiran.portfoliomainadmin.data.mapper.toContentsAndTitle
import com.alqiran.portfoliomainadmin.repository.FirebaseRepository
import com.alqiran.portfoliomainadmin.ui.model.ContentTitleUiModel
import com.alqiran.portfoliomainadmin.ui.model.ContentUiModel
import com.alqiran.portfoliomainadmin.ui.screens.admin.AdminState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ContentsAdminViewModel @Inject constructor(
    private val uploadRepo: FirebaseRepository
) : ViewModel() {
    private val _state = MutableStateFlow<AdminState>(AdminState.None)
    val state = _state.asStateFlow()

    fun uploadContentsAndTitles(allContents: List<ContentTitleUiModel>) {
        _state.value = AdminState.Loading
        try {
            uploadRepo.uploadContentsAndTitle(allContents.toContentsAndTitle())
            _state.value = AdminState.Success
        } catch (e: Exception) {
            _state.value = AdminState.Error(e.message.toString())
        }
    }

    fun deleteContentAndTitle(content: ContentTitleUiModel) {
        _state.value = AdminState.Loading
        try {
            uploadRepo.deleteContentAndTitle(content.toContentAndTitle())
            _state.value = AdminState.Success
        } catch (e: Exception) {
            _state.value = AdminState.Error(e.message.toString())
        }
    }

    fun deleteContent(content: ContentUiModel) {
        _state.value = AdminState.Loading
        try {
            uploadRepo.deleteContent(content.toContent())
            _state.value = AdminState.Success
        } catch (e: Exception) {
            _state.value = AdminState.Error(e.message.toString())
        }
    }

    fun stateNone() {
        _state.value = AdminState.None
    }

}