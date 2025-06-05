package com.alqiran.portflio.ui.screens.courses_screen.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alqiran.portflio.domain.repository.FirebaseRepository
import com.alqiran.portflio.ui.mapper.toCoursesDataUi
import com.alqiran.portflio.ui.mapper.toProjectsDataUi
import com.alqiran.portflio.ui.screens.projects_screen.viewModel.ProjectsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoursesViewModel @Inject constructor(
    private val coursesRepository: FirebaseRepository
): ViewModel() {

    private val _coursesState = MutableStateFlow<CoursesState>(CoursesState.None)
    val coursesState = _coursesState.asStateFlow()

    fun fetchAllCourses() {
        viewModelScope.launch(Dispatchers.IO) {
            _coursesState.value = CoursesState.Loading
            try {
                val courses = coursesRepository.getAllCourses().toCoursesDataUi()
                _coursesState.value = CoursesState.Success(courses)
            } catch (e: Exception) {
                _coursesState.value = CoursesState.Error(e.message ?: "Unknown Error")
            }
        }
    }


}