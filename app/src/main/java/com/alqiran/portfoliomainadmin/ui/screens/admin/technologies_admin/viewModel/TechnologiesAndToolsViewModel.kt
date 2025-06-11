package com.alqiran.portfoliomainadmin.ui.screens.admin.technologies_admin.viewModel

import androidx.lifecycle.ViewModel
import com.alqiran.portfoliomainadmin.data.mapper.toProject
import com.alqiran.portfoliomainadmin.data.mapper.toProjects
import com.alqiran.portfoliomainadmin.data.mapper.toTechnologiesAndTools
import com.alqiran.portfoliomainadmin.data.mapper.toTechnology
import com.alqiran.portfoliomainadmin.data.mapper.toTechnologyAndTool
import com.alqiran.portfoliomainadmin.repository.FirebaseRepository
import com.alqiran.portfoliomainadmin.ui.model.ProjectUiModel
import com.alqiran.portfoliomainadmin.ui.model.TechnologyTitleUiModel
import com.alqiran.portfoliomainadmin.ui.model.TechnologyUiModel
import com.alqiran.portfoliomainadmin.ui.screens.admin.AdminState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class TechnologiesAndToolsAdminViewModel @Inject constructor(
    private val uploadRepo: FirebaseRepository
) : ViewModel() {
    private val _state = MutableStateFlow<AdminState>(AdminState.None)
    val state = _state.asStateFlow()

    fun uploadTechnologiesAndTools(allTechnologies: List<TechnologyTitleUiModel>) {
        _state.value = AdminState.Loading
        try {
            uploadRepo.uploadTechnologiesAndTools(allTechnologies.toTechnologiesAndTools())
            _state.value = AdminState.Success
        } catch (e: Exception) {
            _state.value = AdminState.Error(e.message.toString())
        }
    }

    fun deleteTechnologiesAndTools(technology: TechnologyTitleUiModel) {
        _state.value = AdminState.Loading
        try {
            uploadRepo.deleteTechnologyAndTool(technology.toTechnologyAndTool())
            _state.value = AdminState.Success
        } catch (e: Exception) {
            _state.value = AdminState.Error(e.message.toString())
        }
    }

    fun deleteTechnology(technology: TechnologyUiModel) {
        _state.value = AdminState.Loading
        try {
            uploadRepo.deleteTechnology(technology.toTechnology())
            _state.value = AdminState.Success
        } catch (e: Exception) {
            _state.value = AdminState.Error(e.message.toString())
        }
    }

    fun stateNone() {
        _state.value = AdminState.None
    }

}