package com.alqiran.portfoliomainadmin.ui.screens.admin.courses_admin.viewModel

import androidx.lifecycle.ViewModel
import com.alqiran.portfoliomainadmin.data.mapper.toCourse
import com.alqiran.portfoliomainadmin.data.mapper.toCourses
import com.alqiran.portfoliomainadmin.repository.FirebaseRepository
import com.alqiran.portfoliomainadmin.ui.model.CourseUiModel
import com.alqiran.portfoliomainadmin.ui.screens.admin.AdminState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CoursesAdminViewModel @Inject constructor(
    private val uploadRepo: FirebaseRepository
) : ViewModel() {
    private val _state = MutableStateFlow<AdminState>(AdminState.None)
    val state = _state.asStateFlow()

    fun uploadEducationData(courses: List<CourseUiModel>) {
        _state.value = AdminState.Loading
        try {
            uploadRepo.uploadCourses(courses.toCourses())
            _state.value = AdminState.Success
        } catch (e: Exception) {
            _state.value = AdminState.Error(e.message.toString())
        }
    }

    fun deleteCourse(course: CourseUiModel) {
        _state.value = AdminState.Loading
        try {
            uploadRepo.deleteCourse(course.toCourse())
            _state.value = AdminState.Success
        } catch (e: Exception) {
            _state.value = AdminState.Error(e.message.toString())
        }
    }


    fun stateNone() {
        _state.value = AdminState.None
    }

}