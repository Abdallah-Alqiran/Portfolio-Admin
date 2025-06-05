package com.alqiran.portflio.ui.screens.projects_screen.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alqiran.portflio.domain.repository.FirebaseRepository
import com.alqiran.portflio.ui.mapper.toProjectsDataUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProjectsViewModel @Inject constructor(
    private val projectsRepository: FirebaseRepository
): ViewModel()  {

    private val _projectsState = MutableStateFlow<ProjectsState>(ProjectsState.None)
    val projectState = _projectsState.asStateFlow()

    fun fetchAllProjects() {
        viewModelScope.launch(Dispatchers.IO) {
            _projectsState.value = ProjectsState.Loading
            try {
                val projects = projectsRepository.getAllProjects().toProjectsDataUi()
                _projectsState.value = ProjectsState.Success(projects)
            } catch (e: Exception) {
                _projectsState.value = ProjectsState.Error(e.message ?: "Unknown Error")
            }
        }
    }
}