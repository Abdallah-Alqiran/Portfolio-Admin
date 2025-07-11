package com.alqiran.portfoliomainadmin.ui.screens.admin.education_admin.viewModel

import androidx.lifecycle.ViewModel
import com.alqiran.portfoliomainadmin.data.mapper.toEducation
import com.alqiran.portfoliomainadmin.data.mapper.toEducations
import com.alqiran.portfoliomainadmin.repository.FirebaseRepository
import com.alqiran.portfoliomainadmin.ui.model.EducationUiModel
import com.alqiran.portfoliomainadmin.ui.screens.admin.AdminState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class EducationAdminViewModel @Inject constructor(
    private val uploadRepo: FirebaseRepository
) : ViewModel() {
    private val _state = MutableStateFlow<AdminState>(AdminState.None)
    val state = _state.asStateFlow()

    fun uploadEducationData(allEducation: List<EducationUiModel>) {
        _state.value = AdminState.Loading
        try {
            uploadRepo.uploadEducation(allEducation.toEducations())
            _state.value = AdminState.Success
        } catch (e: Exception) {
            _state.value = AdminState.Error(e.message.toString())
        }
    }

    fun deleteEducation(education: EducationUiModel) {
        _state.value = AdminState.Loading
        try {
            uploadRepo.deleteEducation(education.toEducation())
            _state.value = AdminState.Success
        } catch (e: Exception) {
            _state.value = AdminState.Error(e.message.toString())
        }
    }


    fun stateNone() {
        _state.value = AdminState.None
    }

}