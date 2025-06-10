package com.alqiran.portfoliomainadmin.ui.screens.admin.about_admin.viewModel

import androidx.lifecycle.ViewModel
import com.alqiran.portfoliomainadmin.repository.FirebaseRepository
import com.alqiran.portfoliomainadmin.ui.screens.admin.AdminState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AboutAdminViewModel @Inject constructor(
    private val uploadRepo: FirebaseRepository
) : ViewModel() {
    private val _state = MutableStateFlow<AdminState>(AdminState.None)
    val state = _state.asStateFlow()

    fun editAbout(about: String) {
        _state.value = AdminState.Loading
        try {
            uploadRepo.editAbout(about)
            _state.value = AdminState.Success
        } catch (e: Exception) {
            _state.value = AdminState.Error(e.message.toString())
        }
    }

    fun stateNone() {
        _state.value = AdminState.None
    }

}