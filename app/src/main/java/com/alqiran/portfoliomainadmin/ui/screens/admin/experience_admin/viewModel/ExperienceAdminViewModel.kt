package com.alqiran.portfoliomainadmin.ui.screens.admin.experience_admin.viewModel

import androidx.lifecycle.ViewModel
import com.alqiran.portfoliomainadmin.data.mapper.toExperience
import com.alqiran.portfoliomainadmin.data.mapper.toExperiences
import com.alqiran.portfoliomainadmin.repository.FirebaseRepository
import com.alqiran.portfoliomainadmin.ui.model.ExperienceUiModel
import com.alqiran.portfoliomainadmin.ui.screens.admin.AdminState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ExperienceAdminViewModel @Inject constructor(
    private val uploadRepo: FirebaseRepository
) : ViewModel() {
    private val _state = MutableStateFlow<AdminState>(AdminState.None)
    val state = _state.asStateFlow()

    fun uploadExperience(allExperience: List<ExperienceUiModel>) {
        _state.value = AdminState.Loading
        try {
            uploadRepo.uploadExperience(allExperience.toExperiences())
            _state.value = AdminState.Success
        } catch (e: Exception) {
            _state.value = AdminState.Error(e.message.toString())
        }
    }

    fun deleteExperience(experience: ExperienceUiModel) {
        _state.value = AdminState.Loading
        try {
            uploadRepo.deleteExperience(experience.toExperience())
            _state.value = AdminState.Success
        } catch (e: Exception) {
            _state.value = AdminState.Error(e.message.toString())
        }
    }


    fun stateNone() {
        _state.value = AdminState.None
    }

}