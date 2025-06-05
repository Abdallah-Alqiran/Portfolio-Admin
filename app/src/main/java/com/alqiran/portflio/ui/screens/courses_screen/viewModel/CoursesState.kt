package com.alqiran.portflio.ui.screens.courses_screen.viewModel

import com.alqiran.portflio.ui.model.CourseUiModel


sealed class CoursesState {
    object None: CoursesState()
    object Loading: CoursesState()

    data class Success(val courses: List<CourseUiModel>): CoursesState()
    data class Error(val error: String): CoursesState()
}