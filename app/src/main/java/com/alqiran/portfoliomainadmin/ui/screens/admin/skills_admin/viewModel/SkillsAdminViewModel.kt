package com.alqiran.portfoliomainadmin.ui.screens.admin.skills_admin.viewModel

import androidx.lifecycle.ViewModel
import com.alqiran.portfoliomainadmin.data.mapper.toEducation
import com.alqiran.portfoliomainadmin.data.mapper.toSkill
import com.alqiran.portfoliomainadmin.data.mapper.toSkills
import com.alqiran.portfoliomainadmin.repository.FirebaseRepository
import com.alqiran.portfoliomainadmin.ui.model.SkillUiModel
import com.alqiran.portfoliomainadmin.ui.screens.admin.AdminState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SkillsAdminViewModel @Inject constructor(
    private val uploadRepo: FirebaseRepository
) : ViewModel() {
    private val _state = MutableStateFlow<AdminState>(AdminState.None)
    val state = _state.asStateFlow()

    fun uploadSkills(skills: List<SkillUiModel>) {
        _state.value = AdminState.Loading
        try {
            uploadRepo.uploadSkills(skills.toSkills())
            _state.value = AdminState.Success
        } catch (e: Exception) {
            _state.value = AdminState.Error(e.message.toString())
        }
    }

    fun deleteSkill(skill: SkillUiModel) {
        _state.value = AdminState.Loading
        try {
            uploadRepo.deleteSkill(skill.toSkill())
            _state.value = AdminState.Success
        } catch (e: Exception) {
            _state.value = AdminState.Error(e.message.toString())
        }
    }

    fun stateNone() {
        _state.value = AdminState.None
    }
}