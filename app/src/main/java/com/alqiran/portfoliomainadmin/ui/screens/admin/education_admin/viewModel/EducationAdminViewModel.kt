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
    private val _educationAdminState = MutableStateFlow<AdminState>(AdminState.None)
    val educationAdminState = _educationAdminState.asStateFlow()

    fun uploadEducationData(allEducation: List<EducationUiModel>) {
        _educationAdminState.value = AdminState.Loading
        try {
            uploadRepo.uploadEducation(allEducation.toEducations())
            _educationAdminState.value = AdminState.Success
        } catch (e: Exception) {
            _educationAdminState.value = AdminState.Error(e.message.toString())
        }
    }

    fun deleteEducation(education: EducationUiModel) {
        _educationAdminState.value = AdminState.Loading
        try {
            uploadRepo.deleteEducation(education.toEducation())
            _educationAdminState.value = AdminState.Success
        } catch (e: Exception) {
            _educationAdminState.value = AdminState.Error(e.message.toString())
        }
    }


    fun stateNone() {
        _educationAdminState.value = AdminState.None
    }

}