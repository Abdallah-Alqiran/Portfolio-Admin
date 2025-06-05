package com.alqiran.portflio.ui.screens.project_item_screen.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alqiran.portflio.domain.repository.FirebaseRepository
import com.alqiran.portflio.ui.mapper.toProjectItemDataUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProjectItemViewModel @Inject constructor(
    private val projectItemRepository: FirebaseRepository
) : ViewModel() {

    private val _projectState = MutableStateFlow<ProjectItemState>(ProjectItemState.None)
    val projectState = _projectState.asStateFlow()

    fun fetchProjectItem(id: Int) {
        viewModelScope.launch {
            _projectState.value = ProjectItemState.Loading
            try {
                val projectItemData = projectItemRepository.getProjectItem(id).toProjectItemDataUi()
                _projectState.value = ProjectItemState.Success(projectItemData)
            } catch (e: Exception) {
                _projectState.value = ProjectItemState.Error(e.message.toString())
            }
        }
    }

}